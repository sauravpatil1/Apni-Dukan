package com.saurav.apnidukan.model;

public class Category {
    Integer id;
    Integer imageURL;
    String type;

    public Category(Integer imageURL, String type) {
        this.imageURL = imageURL;
        this.type = type;
    }

    public Category(Integer id, Integer imageURL, String type) {
        this.id = id;
        this.imageURL = imageURL;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getImageURL() {
        return imageURL;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageURL(Integer imageURL) {
        this.imageURL = imageURL;
    }
}
