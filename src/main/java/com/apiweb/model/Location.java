package com.apiweb.model;

public class Location {

    private int id;

    private String number;

    private String street;

    private String city;

    private int zipcode;

    private String country;

    public Location(int id, String number, String street, String city, int zipcode, String country) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    @Override
    public String toString() {
        return number + " " + street + ", " + zipcode + " " + city + ", " + country;
    }

    public int getId() { return id; }
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
