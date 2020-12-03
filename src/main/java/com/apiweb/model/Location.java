package com.apiweb.model;

import com.apiweb.controller.GenerateId;
import com.google.cloud.firestore.DocumentSnapshot;

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

    public Location(DocumentSnapshot document) {
        this.id = document.getId();
        this.number = document.getString("number");
        this.street = document.getString("street");
        this.city = document.getString("city");
        this.zipcode = Math.toIntExact((long) document.get("zipcode"));
        this.country = document.getString("country");
    }

    @Override
    public String toString() {
        return number + " " + street + ", " + zipcode + " " + city + ", " + country;
    }

    public String getId() { return id; }
    public void setId() {
        if (this.id == "" || this.id == null) {
            this.id = GenerateId.generateID();
        }
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
