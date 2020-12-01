package com.apiweb.model;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;

public class Meeting {

    private int id;

    private String name;

    private DateTime date;

    private Location location;

    private ArrayList<Person> contributors;


    public Meeting(int id, String name, DateTime date, Location location, ArrayList<Person> contributors) {
        this.id = id;
        this.name = name;
        this.date = date;
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DateTime getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Person> getContributors() {
        return contributors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setContributors(ArrayList<Person> contributors) {
        this.contributors = contributors;
    }
    }
