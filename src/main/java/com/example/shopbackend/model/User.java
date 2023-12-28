package com.example.shopbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(mappedBy = "user")
    private Cart cart;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(){}
    public User(String username, String password,
                String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public User(String username, String password,
                String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User CREATEACCOUNTWITHOUTPHONENUMBER(String username, String password, String salt,
                                                       String email) {
        return new User(username, password, salt,
                email);
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
