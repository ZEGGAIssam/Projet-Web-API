package com.apiweb.controller;

import com.apiweb.model.MeetingPoll;
import com.apiweb.model.User;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.storage.Acl;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
public class RestController {

    private User current_user = null;
    private MeetingPoll current_meeting = null;
    private HashMap<String, User> auth = new HashMap<String, User>();

    //user routed
    @PostMapping("/login")
    public String getCurrentUser(@RequestBody Map<String, Object> json) throws InterruptedException, ExecutionException {
        current_user = FirebaseServiceUser.getConnected(json.get(LOGIN).toString());
        if (current_user != null && current_user.verifiedPsw(json.get(PWD).toString()))
        {
           System.out.println(current_user);
          return "1";
        }
        else
        {
            current_user = null;
            return "0";
        }
    }
    @PostMapping("/register")
    public String addUser(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException {
        if (!FirebaseServiceUser.getLoginAlreadyExist(json.get(LOGIN).toString())) {
            User userToAdd = new User(json.get(FIRSTNAME).toString(), json.get(NAME).toString(), json.get(LOGIN).toString(), json.get(PWD).toString());
            FirebaseServiceUser.save(userToAdd);
            current_user = userToAdd;
            return "1";
        } else {
            return "0";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException, FirebaseAuthException {
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
        current_meeting.addVote(json.get("voteLocation").toString(), json.get("voteDate").toString(), current_user);
        FirebaseServiceMeetingPoll.saveMeeting(current_meeting);
        FirebaseServiceUser.save(current_user);
    }

    @GetMapping("/getAllMeeting")
    public ArrayList<Object> getAllMeeting() throws InterruptedException, ExecutionException {
        return  FirebaseServiceMeetingPoll.getAll();
    }

}
