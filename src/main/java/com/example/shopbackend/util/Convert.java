package com.example.shopbackend.util;

import com.example.shopbackend.dto.CartDto;
import com.example.shopbackend.dto.OrderDto;
import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.model.Cart;
import com.example.shopbackend.model.Order;
import com.example.shopbackend.model.User;

public class Convert {
    public static User dtoToUser(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();

        User user = new User(
                username,
                password,
                email,
                phoneNumber
        );
        user.setId(userDto.getId());
        user.setRole(userDto.getRole());
        return user;
    }
    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber()
        );
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        return userDto;
    }

    public static Order dtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setAddress(orderDto.getAddress());
        order.setQuantity(orderDto.getQuantity());
        order.setStatus(orderDto.getStatus());
        order.setPhoneNumber(orderDto.getPhoneNumber());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setRefund(orderDto.getRefund());
        return order;
    }

    public static OrderDto orderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setStatus(order.getStatus());
        orderDto.setPhoneNumber(order.getPhoneNumber());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setRefund(order.getRefund());
        return orderDto;
    }

    public static CartDto cartToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setTotalPrice(cart.getTotalPrice());
        cartDto.setProducts(cart.getProducts());
        return cartDto;
    }

    public static Cart dtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setTotalPrice(cartDto.getTotalPrice());
        cart.setProducts(cartDto.getProducts());
        return cart;
    }
}
