package com.example.shopbackend.dto;

import java.util.List;

public class UserDto {
    private long id;
    private String username;
    private String email;
    private String phoneNumber;
    private CartDto cartDto;
    private List<OrderDto> orderDtos;
    public UserDto(
            long id, String username, String email,
            String phoneNumber, CartDto cartDto,
            List<OrderDto> orderDtos
    ){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cartDto = cartDto;
        this.orderDtos = orderDtos;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public void setOrderDtos(List<OrderDto> orderDtos) {
        this.orderDtos = orderDtos;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public List<OrderDto> getOrderDtos() {
        return orderDtos;
    }
}
