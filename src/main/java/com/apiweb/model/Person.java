package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.type.DateTime;

import java.util.Date;

public class Person {

    private String id;

    private String firstname;

    private String name;

    private Date birthday;

    public Person(String id, String firstname, String name, Date birthday) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.birthday = birthday;
    }

    public Person(DocumentSnapshot document) {
        this.id = document.getId();
        this.firstname = document.getString("firstname");
        this.name = document.getString("name");
        this.birthday = document.getDate("birthday");
    }

    public String toString() {
        return "User : " +firstname + " " + name+ "\nNée le :" + birthday;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
