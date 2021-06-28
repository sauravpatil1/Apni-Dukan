package com.saurav.apnidukan.model;


public class Order {
    String id, userId, productId, shopId, status, userName, userAddress;

    public Order(String id, String userId, String productId, String shopId, String status, String userName, String userAddress) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.shopId = shopId;
        this.status = status;
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getShopId() {
        return shopId;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
