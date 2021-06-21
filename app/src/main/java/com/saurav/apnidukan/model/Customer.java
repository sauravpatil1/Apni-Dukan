package com.saurav.apnidukan.model;

public class Customer {
    Integer id, mobileNumber;
    String name, address;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Customer(Integer id, Integer mobileNumber, String name, String address) {
        this.id = id;
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
