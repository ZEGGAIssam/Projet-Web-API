package com.apiweb.controller;

import com.apiweb.model.Person;
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
    public String getExample(@RequestHeader String id) throws InterruptedException, ExecutionException {
        return firebaseServiceUser.getUserDetails(id).toString();
    }
    @PostMapping("/User")
    public String postExample(@RequestBody Person person) throws InterruptedException, ExecutionException{
        person.setId();
        System.out.println(person.getId());
        return firebaseServiceUser.saveUserDetails(person);
    }

    @PutMapping("/User")
    public String putExample(@RequestBody Person person) throws ExecutionException, InterruptedException {
        return firebaseServiceUser.updateUserDetails(person);
    }
    @DeleteMapping("/User")
    public String deleteExample(@RequestParam String id) throws ExecutionException, InterruptedException {
        return firebaseServiceUser.deleteUser(id);
    }
}
