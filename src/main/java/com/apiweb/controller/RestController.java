package com.apiweb.controller;

import com.apiweb.model.Location;
import com.apiweb.model.MeetingPoll;
import com.apiweb.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.apiweb.var.*;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    private Person current_user = null;
    //user route
    @CrossOrigin
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
            return "user ou mdp incorect";
        }
    }
    @CrossOrigin
    @PostMapping("/register")
    public String addUser(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException {
        if (!FirebaseServiceUser.getLoginAlreadyExist(json.get(LOGIN).toString())) {
            Person userToAdd = new Person(json.get(FIRSTNAME).toString(), json.get(NAME).toString(), json.get(LOGIN).toString(), json.get(PWD).toString());
            FirebaseServiceUser.saveUser(userToAdd);
            current_user = userToAdd;
            return "1";
        } else {
            return "0";
        }
    }

    //user route
    @GetMapping("/User")
    public Person getUser(@RequestParam String id) throws InterruptedException, ExecutionException {
        return (FirebaseServiceUser.getUser(id));
    }
    @PostMapping("/User")
    public String postUser(@RequestBody Person person) throws InterruptedException, ExecutionException{
        return FirebaseServiceUser.saveUser(person);
    }

    @DeleteMapping("/User")
    public String deleteUser(@RequestParam String id) throws ExecutionException, InterruptedException {
        return FirebaseServiceUser.deleteUser(id);
    }

    //location route
    @GetMapping("/Location")
    public Location getLocation(@RequestParam String id) throws InterruptedException, ExecutionException {
        return (FirebaseServiceLocation.getLocation(id));
    }
    @PostMapping("/Location")
    public String postLocation(@RequestBody Location location) throws InterruptedException, ExecutionException{
        return FirebaseServiceLocation.saveLocation(location);
    }

    @DeleteMapping("/Location")
    public String deleteLocation(@RequestParam String id) throws ExecutionException, InterruptedException {
        return FirebaseServiceLocation.deleteLocation(id);
    }

    //route Meeting
    @GetMapping("/Meeting")
    public MeetingPoll getMeeting(@RequestParam String id) throws Exception {
        return FirebaseServiceMeeting.getMeeting(id);
    }

    @PostMapping("/Meeting")
    public String postMeeting(@RequestBody MeetingPoll meetingPoll) throws InterruptedException, ExecutionException {
        return FirebaseServiceMeeting.saveMeeting(meetingPoll);
    }


    @DeleteMapping("/Meeting")
    public String deleteMeeting(@RequestParam String id) throws InterruptedException, ExecutionException {
        return FirebaseServiceMeeting.deleteMeeting(id);
    }

    @PostMapping("/addContributorMeeting")
    public String addContributorMeeting(@RequestBody Map<String, Object> json) throws ExecutionException, InterruptedException {
        return FirebaseServiceContributor.addContributortoMeeting(json);
    }
}
