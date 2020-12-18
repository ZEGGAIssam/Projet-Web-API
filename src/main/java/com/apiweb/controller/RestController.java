package com.apiweb.controller;

import com.apiweb.model.Authentification;
import com.apiweb.model.MeetingPoll;
import com.apiweb.model.User;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
public class RestController {

    //user routed
    @PostMapping("/login")
    public String getCurrentUser(@RequestBody Map<String, Object> json, HttpServletResponse response) throws InterruptedException, ExecutionException {
        User current_user = FirebaseServiceUser.getConnected(json.get(LOGIN).toString());
        if (current_user != null && current_user.verifiedPsw(json.get(PWD).toString())) {
            System.out.println(current_user);
            response.setHeader("Set-Cookie", TOKEN + "=" + Authentification.generateToken(current_user) + ";HttpOnly; SameSite=strict");
            return "1";
        } else {
            return "login or password invalid";
        }
    }

    @PostMapping("/register")
    public String addUser(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException {
        if (!FirebaseServiceUser.getLoginAlreadyExist(json.get(LOGIN).toString())) {
            User userToAdd = new User(json.get(FIRSTNAME).toString(), json.get(NAME).toString(), json.get(LOGIN).toString(), json.get(PWD).toString());
            FirebaseServiceUser.save(userToAdd);
            return "1";
        } else {
            return "0";
        }
    }

    @PostMapping("/logout")
    public String logout( @CookieValue(TOKEN) String token) throws ExecutionException, InterruptedException, FirebaseAuthException {
        Authentification.authArray.remove(token);
        return "1";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody Map<String, Object> json, @CookieValue(TOKEN) String token) throws ExecutionException, InterruptedException, FirebaseAuthException {
        User current_user = Authentification.getByToken(token);
        if  (current_user != null) {
            current_user.setName(json.get(NAME).toString());
            current_user.setFirstname(json.get(FIRSTNAME).toString());
            FirebaseServiceUser.save(current_user);
            return "Updated user" + current_user.getName() + current_user.getFirstname();
        }
        else
        {
            return "0";
        }
    }

    @PostMapping("/createMeetingPoll")
    public String addMeetingPoll(@RequestBody Map<String, Object> json, @CookieValue(TOKEN) String token) throws InterruptedException, ExecutionException, ParseException {
        if (Authentification.isValid(token)) {
            HashMap choixDate = new HashMap<Date, Long>();
            choixDate.put(json.get("date1").toString(), 0);
            choixDate.put(json.get("date2").toString(), 0);
            choixDate.put(json.get("date3").toString(), 0);
            HashMap choixLoc = new HashMap<String, Long>();
            choixLoc.put(json.get("location1").toString(), 0);
            choixLoc.put(json.get("location2").toString(), 0);
            choixLoc.put(json.get("location3").toString(), 0);
            MeetingPoll meetingPoll = new MeetingPoll(json.get(NAME).toString(), choixDate, choixLoc);
            User current_user = Authentification.getByToken(token);
            current_user.addIdCreated(meetingPoll.getId());
            FirebaseServiceMeetingPoll.add(meetingPoll);
            FirebaseServiceUser.save(current_user);
            return "1";
        } else {
            return "0";
        }

    }


    @GetMapping("/getMeeting")
    public String getMeeting(@RequestBody Map<String, Object> json) throws InterruptedException, ExecutionException {
        MeetingPoll current_meeting = FirebaseServiceMeetingPoll.get(json.get(ID).toString());
        return current_meeting.getName();
    }

    @PostMapping("/saveVote")
    public String addVoteLocation(@RequestBody Map<String, Object> json, @CookieValue(TOKEN) String token) throws InterruptedException, ExecutionException, ParseException {
        User current_user = Authentification.getByToken(token);
        String voteLocation = json.get("voteLocation").toString();
        String voteDate = json.get("voteDate").toString();
        if (current_user != null && voteLocation != "" && voteDate != "") {
            MeetingPoll current_meeting = FirebaseServiceMeetingPoll.get(json.get(ID).toString());
            current_meeting.addVote(voteLocation, voteDate, current_user);
            FirebaseServiceMeetingPoll.saveMeeting(current_meeting);
            FirebaseServiceUser.save(current_user);
            return "1";
        }
        else if (current_user == null) {
            return "invalid token";
        }
        else if (voteLocation == "" || voteDate == "")
        {
            return "vote item invalid";
        }
        else
        {
            return "0";
        }
    }

    @GetMapping("/getAllMeeting")
    public Object getAllMeeting(@CookieValue(TOKEN) String token) throws InterruptedException, ExecutionException {
        User current_user = Authentification.getByToken(token);
        if (current_user != null) {
            ArrayList<Object> meetings = FirebaseServiceMeetingPoll.getAll();
            ArrayList<Object> toRemove = new ArrayList<>();
            for (Object o: meetings) {
                if (current_user.getIdMeetingVoted().contains(((HashMap)o).get("id")))
                {
                    toRemove.add(o);
                }
            }
            for (Object o :toRemove) {
                meetings.remove(o);
            }
            return meetings;
        } else {
            return "invalid token";
        }
    }
    @PostMapping("/deleteAMeeting")
    public Object deleteAMeeting(@RequestBody Map<String, Object> json, @CookieValue(TOKEN) String token) throws InterruptedException, ExecutionException {
        User current_user = Authentification.getByToken(token);
        if (current_user != null) {
            FirebaseServiceMeetingPoll.deleteMeeting(json.get(ID).toString());
            current_user.removeIdCreated(json.get(ID).toString());
            FirebaseServiceUser.save(current_user);
            return "1";
        }
        else
        {
            return "invalid token";
        }
    }


    @GetMapping("/getMyMeeting")
    public Object getMyMeeting(@CookieValue(TOKEN) String token) throws InterruptedException, ExecutionException {
        User current_user = Authentification.getByToken(token);
        if (current_user != null) {
            ArrayList<Object> myMeetings = new ArrayList<>();
            for (String id: current_user.getIdMeetingCreated()) {
                    myMeetings.add(FirebaseServiceMeetingPoll.get(id));
            }
            return myMeetings;
        } else {
            return "invalid token";
        }
    }
}
