package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;

import static com.apiweb.var.DATE;
import static com.apiweb.var.NAME;

public class Meeting {

    private String id;

    private String name;

    private Date date;

    private Location location;

    private ArrayList<Person> contributors;


    public Meeting(String id, String name, Date date, Location location, ArrayList<Person> contributors) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.contributors = contributors;
    }

    public Meeting(DocumentSnapshot document, Location location, ArrayList<Person> contributors) {
        this.id = document.getId();
        this.name = document.getString(NAME);
        this.date = document.getDate(DATE);
        this.location = location;
        this.contributors = contributors;
    }

    @Override
    public String toString() {
        String listContributor = "";
        for (Person p: contributors) {
            listContributor = listContributor + " " + p + ",";
        }
        listContributor = listContributor.substring(0, listContributor.length() - 1);
        return "Rendez-vous " + name + "\nLieu : " + location + "\nDate : " + date + "\nAvec :" + listContributor;
    }

    public void addContributor(Person c)
    {
        contributors.add(c);
    }

    public void removeContributorr(Person c)
    {
        contributors.remove(c);
    }

    public String getId() {
        return id;
    }

    public void setId() {
        if (this.id == "" || this.id == null) {
            this.id = GenerateId.generateID();
        }
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Person> getContributors() {
        return contributors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setContributors(ArrayList<Person> contributors) {
        this.contributors = contributors;
    }
    }
