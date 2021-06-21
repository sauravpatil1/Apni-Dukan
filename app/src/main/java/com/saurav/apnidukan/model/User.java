package com.saurav.apnidukan.model;

public class User {
    public String id, phone, email, password, name, address, shopID;
    public boolean isShopKeeper;

    public User(){}

    public User(String phone, String email, String password, String name) {
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isShopKeeper() {
        return isShopKeeper;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setShopKeeper(boolean shopKeeper) {
        isShopKeeper = shopKeeper;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
