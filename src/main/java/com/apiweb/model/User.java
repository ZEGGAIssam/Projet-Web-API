package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

import static com.apiweb.var.*;

public class User {

    private String id;

    private String firstname;

    private String name;

    private String username;
    private String password;
    private ArrayList<String> idMeetingVoted;


    public User(String id, String firstname, String name, String username, String password, ArrayList<String> idMeetingVoted) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.username = username;
        this.password = password;
        this.idMeetingVoted = idMeetingVoted;
    }

    public User(String firstname, String name, String username, String password) {
        this.id = GenerateId.generateID();
        this.firstname = firstname;
        this.name = name;
        this.username = username;
        this.password = password;
        this.idMeetingVoted = new ArrayList<String>();
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

    public void addId(String id) {
        idMeetingVoted.add(id);
    }

    public ArrayList<String> getIdMeetingVoted()
    {
        return  idMeetingVoted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
