package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.Date;

import static com.apiweb.var.*;

public class Person {

    private String id;

    private String firstname;

    private String name;

    private String username;
    private String password;


    public Person(String id, String firstname, String name, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Person(String firstname, String name,  String username, String password) {
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

    public void setId() {
        if (this.id == "" || this.id == null) {
            this.id = GenerateId.generateID();
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean verifiedPsw(String pwd) {
        return pwd == this.password;
    }
}
