package com.apiweb.controller;

import com.apiweb.model.Location;
import com.apiweb.model.Person;
import com.apiweb.service.FirebaseServiceLocation;
import com.apiweb.service.FirebaseServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Autowired
    FirebaseServiceUser firebaseServiceUser;
    //user route
    @GetMapping("/User")
    public String getUser(@RequestParam String id) throws InterruptedException, ExecutionException {
        return (firebaseServiceUser.getUser(id)).toString();
    }
    @PostMapping("/User")
    public String postUser(@RequestBody Person person) throws InterruptedException, ExecutionException{
        person.setId();
        System.out.println(person.getId());
        return firebaseServiceUser.saveUser(person);
    }

    @PutMapping("/User")
    public String putUser(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseServiceUser.updateUser(person);
    }
    @DeleteMapping("/User")
    public String deleteUser(@RequestParam String id) throws ExecutionException, InterruptedException {
        return firebaseServiceUser.deleteUser(id);
    }

    @Autowired
    FirebaseServiceLocation firebaseServiceLocation;
    //location route
    @GetMapping("/Location")
    public String getLocation(@RequestParam String id) throws InterruptedException, ExecutionException {
        return (firebaseServiceLocation.getLocation(id)).toString();
    }
    @PostMapping("/Location")
    public String postLocation(@RequestBody Location location) throws InterruptedException, ExecutionException{
        location.setId();
        System.out.println(location.getId());
        return firebaseServiceLocation.saveLocation(location);
    }

    @PutMapping("/Location")
    public String putLocation(@RequestBody Location location) throws ExecutionException, InterruptedException {
        return firebaseServiceLocation.updateLocation(location);
    }
    @DeleteMapping("/Location")
    public String deleteLocation(@RequestParam String id) throws ExecutionException, InterruptedException {
        return firebaseServiceLocation.deleteLocation(id);
    }
}
