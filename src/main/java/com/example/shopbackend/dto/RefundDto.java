package com.example.shopbackend.dto;

import com.example.shopbackend.model.Order;
import com.example.shopbackend.model.User;

public class RefundDto {
    private long id;
    private String status;
    private User user;
    private Order order;
    public RefundDto(){}
    public RefundDto(
            long id,
            String status,
            User user,
            Order order
    ){
        this.id = id;
        this.status = status;
        this.user = user;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
