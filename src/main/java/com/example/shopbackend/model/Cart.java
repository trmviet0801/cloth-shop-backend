package com.example.shopbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    @Column(name = "total_price")
    private double totalPrice;
    @ManyToMany(mappedBy = "carts", cascade = CascadeType.ALL)
    List<Product> products;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Cart(){}
    public Cart(int quantity, double totalPrice, User user){
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        products.add(product);
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

    public void setUser(User user) {
        this.user = user;
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

    public User getUser() {
        return user;
    }
}
