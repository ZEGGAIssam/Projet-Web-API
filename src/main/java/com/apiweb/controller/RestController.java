package com.apiweb.controller;

import com.apiweb.model.Person;
import com.apiweb.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Autowired
    FirebaseService firebaseService;

    @GetMapping("/getUserDetails")
    public Person getExample(@RequestHeader() String name) throws InterruptedException, ExecutionException {
        return firebaseService.getUserDetails(name);
    }
    @PostMapping("/createUser")
    public String postExample(@RequestBody Person person) throws InterruptedException, ExecutionException{
        return firebaseService.saveUserDetails(person);
    }

    @PutMapping("/updateUser")
    public String putExample(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseService.updateUserDetails(person);
    }
    @DeleteMapping("/deleteUser")
    public String deleteExample(@RequestHeader String name)  {
        return firebaseService.deleteUser(name);
    }
}
