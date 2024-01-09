package com.example.shopbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int totalView;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @ManyToMany
    @JoinTable(name = "product_cart",
    joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"))
    List<Cart> carts;

    public Product(){}
    public Product(String name, String description, double price,
                   int quantity, int total_view, Category category ){
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.totalView = total_view;
        this.category = category;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalView() {
        return totalView;
    }

    public Category getCategory() {
        return category;
    }
}
