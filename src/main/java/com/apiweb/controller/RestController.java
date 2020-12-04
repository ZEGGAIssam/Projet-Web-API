package com.apiweb.controller;

import com.apiweb.model.Location;
import com.apiweb.model.Meeting;
import com.apiweb.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    //user route
    @GetMapping("/User")
    public Person getUser(@RequestParam String id) throws InterruptedException, ExecutionException {
        return (FirebaseServiceUser.getUser(id));
    }
    @PostMapping("/User")
    public String postUser(@RequestBody Person person) throws InterruptedException, ExecutionException{
        person.setId(); //ajouter un id si c'est une nouvelle personne
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
        location.setId();
        System.out.println(location.getId());
        return FirebaseServiceLocation.saveLocation(location);
    }

    @DeleteMapping("/Location")
    public String deleteLocation(@RequestParam String id) throws ExecutionException, InterruptedException {
        return FirebaseServiceLocation.deleteLocation(id);
    }

    //route Meeting
    @GetMapping("/Meeting")
    public Meeting getMeeting(@RequestParam String id) throws Exception {
        return FirebaseServiceMeeting.getMeeting(id);
    }

    @PostMapping("/Meeting")
    public String postMeeting(@RequestBody Meeting meeting) throws InterruptedException, ExecutionException {
        return FirebaseServiceMeeting.saveMeeting(meeting);
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
