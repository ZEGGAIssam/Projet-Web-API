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

    private HashMap<String, Long> choixDate ;

    private HashMap<String, Long> choixLocation;

    public MeetingPoll(String name, HashMap<String, Long> choixDate, HashMap<String, Long> choixLocation) {
        this.id = GenerateId.generateID();
        this.name = name;
        this.choixDate = choixDate;
        this.choixLocation = choixLocation;
    }

    public MeetingPoll(String id, String name, HashMap<String, Long> choixDate, HashMap<String, Long> choixLocation) {
        this.id = id;
        this.name = name;
        this.choixDate = choixDate;
        this.choixLocation = choixLocation;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Long> getChoixDate() {
        return choixDate;
    }

    public HashMap<String, Long> getChoixLocation() {
        return choixLocation;
    }

    public void addVote(String keyLoc, String keyDate, User user) {
        Long nbDateVote = choixDate.get(keyDate);
        choixDate.put(keyDate, nbDateVote+1 );
        Long nbLocVote = choixLocation.get(keyLoc);
        choixLocation.put(keyLoc,  nbLocVote+1 );
        user.addId(this.id);
    }
}
