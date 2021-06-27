package com.saurav.apnidukan.model;

public class Product {
    Integer id, imageURL, price, discount;
    String brandName, type, weight, description, image, shopName, shopId, productId;
    Shop shop;
    public Product(){}

    public Product(Integer imageURL) {
        this.imageURL = imageURL;
    }

    public Product(String image, Integer price, Integer discount, String brandName, String type, String weight, String description) {
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.brandName = brandName;
        this.type = type;
        this.weight = weight;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(Integer imageURL, Integer price, String brandName, String description) {
        this.imageURL = imageURL;
        this.price = price;
        this.brandName = brandName;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public Integer getImageURL() {
        return imageURL;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getType() {
        return type;
    }

    public String getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public Shop getShop() {
        return shop;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImageURL(Integer imageURL) {
        this.imageURL = imageURL;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
