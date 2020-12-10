package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.Date;

import static com.apiweb.var.*;

public class User {

    private String id;

    private String firstname;

    private String name;

    private String username;
    private String password;


    public User(String id, String firstname, String name, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String firstname, String name, String username, String password) {
        this.id = GenerateId.generateID();
        this.firstname = firstname;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return "User : " +firstname + " " + name;
    }

    public String getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getUsername()
    {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean verifiedPsw(String pwd) {
        return pwd.equals(this.password);
    }
}
