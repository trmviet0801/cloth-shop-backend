package com.example.shopbackend.dto;

import com.example.shopbackend.model.Product;

import java.util.List;

public class CartDto {
    private long id;
    private int quantity;
    private double totalPrice;
    private List<Product> products;

    public CartDto(){}

    public CartDto(long id, int quantity, double totalPrice, List<Product> products) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
