package com.saurav.apnidukan.model;

public class DiscountedProduct {
    Product product;

    public DiscountedProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
