package com.apiweb.controller;

import com.apiweb.model.Authentification;
import com.apiweb.model.MeetingPoll;
import com.apiweb.model.User;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.rpc.context.AttributeContext;
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

    private MeetingPoll current_meeting = null;


    //user routed
    @PostMapping("/login")
    public String getCurrentUser(@RequestBody  Map<String, Object> json, HttpServletResponse response) throws InterruptedException, ExecutionException {
        User current_user = FirebaseServiceUser.getConnected(json.get(LOGIN).toString());
        if (current_user != null && current_user.verifiedPsw(json.get(PWD).toString()))
        {
            System.out.println(current_user);
            response.setHeader("Set-Cookie", TOKEN+"=" + Authentification.generateToken(current_user) + ";HttpOnly; SameSite=strict");
            return "1";
        }
        else
        {
            return "0";
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

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException, FirebaseAuthException {
            User current_user = FirebaseServiceUser.getConnected("aschneider2");
            current_user.setName(json.get(NAME).toString());
            current_user.setFirstname(json.get(FIRSTNAME).toString());
            FirebaseServiceUser.save(current_user);
            return"Updated user"+ current_user.getName()+ current_user.getFirstname();
        }

    @PostMapping("/createMeetingPoll")
    public String addMeetingPoll(@RequestBody Map<String, Object> json) throws InterruptedException, ExecutionException, ParseException {
        HashMap choixDate = new HashMap<Date, Long>();
        choixDate.put(json.get("date1").toString(), 0);
        choixDate.put(json.get("date2").toString(), 0);
        choixDate.put(json.get("date3").toString(), 0);
        HashMap choixLoc = new HashMap<String, Long>();
        choixLoc.put(json.get("location1").toString(), 0);
        choixLoc.put(json.get("location2").toString(), 0);
        choixLoc.put(json.get("location3").toString(), 0);
        MeetingPoll meetingPoll = new MeetingPoll(json.get(NAME).toString(), choixDate, choixLoc );
        FirebaseServiceMeetingPoll.add(meetingPoll);
        return "1";

    }


    @GetMapping("/getMeeting")
    public String getMeeting(@RequestBody Map<String, Object> json) throws InterruptedException, ExecutionException {
        current_meeting = FirebaseServiceMeetingPoll.get(json.get(ID).toString());
        return  current_meeting.getName();
    }

    @PostMapping("/saveVote")
    public void addVoteLocation(@RequestBody Map<String, Object> json) throws InterruptedException, ExecutionException, ParseException {
        User current_user = Authentification.getByToken(json.get(TOKEN).toString());
        current_meeting.addVote(json.get("voteLocation").toString(), json.get("voteDate").toString(), current_user);
        FirebaseServiceMeetingPoll.saveMeeting(current_meeting);
        FirebaseServiceUser.save(current_user);
    }

    @GetMapping("/getAllMeeting")
    public ArrayList<Object> getAllMeeting(HttpServletRequest resquest) throws InterruptedException, ExecutionException {
        resquest.getCookies();
        return FirebaseServiceMeetingPoll.getAll();
    }

}
