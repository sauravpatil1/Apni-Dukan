package com.saurav.apnidukan.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    public String name, address, imageURL, id, userID;
    double latitude, longitude;
    List<String> productIDList = new ArrayList<>();

    public Shop(String name) {
        this.name = name;
    }

    public Shop(){}

    public Shop(String name, String address, String imageURL, double latitude, double longitude){
        this.name = name;
        this.address = address;
        this.imageURL = imageURL;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
