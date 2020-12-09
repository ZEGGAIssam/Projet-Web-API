package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static com.apiweb.var.DATE;
import static com.apiweb.var.NAME;

public class MeetingPoll {

    private String id;

    private String name;

    private HashMap<Date, Integer> choixDate ;

    private HashMap<Location, Internal> location;

    public MeetingPoll(String name, HashMap<Date, Integer> choixDate, HashMap<Location, Internal> location) {
        this.id = GenerateId.generateID();
        this.name = name;
        this.choixDate = choixDate;
        this.location = location;
    }

    public MeetingPoll(String id, String name, HashMap<Date, Integer> choixDate, HashMap<Location, Internal> location) {
        this.id = id;
        this.name = name;
        this.choixDate = choixDate;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<Date, Integer> getChoixDate() {
        return choixDate;
    }

    public HashMap<Location, Internal> getLocation() {
        return location;
    }
}
