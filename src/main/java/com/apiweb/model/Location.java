package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

import static com.apiweb.var.*;

public class Location {

    private String id;

    private String number;

    private String street;

    private String city;

    private int zipcode;

    private String country;

    public Location(String id, String number, String street, String city, int zipcode, String country) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Location(String number, String street, String city, int zipcode, String country) {
        this.id = GenerateId.generateID();
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }
}
