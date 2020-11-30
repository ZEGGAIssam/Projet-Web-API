package com.example.restapp;

import com.google.type.DateTime;

public class Person {

    private int id;

    private String firstname;

    private String name;

    private DateTime birthday;

    public Person(int id, String firstname, String name, DateTime birthday) {
        this.id = id;
        this.firstname = firstname;
        this.name = name;
        this.birthday = birthday;
    }

    public String toString() {
        return "User : " +firstname + " " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }
}
