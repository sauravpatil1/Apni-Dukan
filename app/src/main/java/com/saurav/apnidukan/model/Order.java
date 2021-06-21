package com.saurav.apnidukan.model;

import java.util.List;

public class Order {
    Integer id, price;
    List<Product> productList;
    Customer customer;
    public Order(Customer customer){
        this.customer = customer;
    }

    public Order(Integer id, Integer price, List<Product> productList, Customer customer) {
        this.id = id;
        this.price = price;
        this.productList = productList;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
