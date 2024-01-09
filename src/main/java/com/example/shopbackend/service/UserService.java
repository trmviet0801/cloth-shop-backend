package com.example.shopbackend.service;

import com.example.shopbackend.dto.OrderDto;
import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.*;
import com.example.shopbackend.model.Order;
import com.example.shopbackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserDto user) throws NotContainRequiredData, DuplicatedUser;
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String PhoneNumber);
    Optional<User> findById(long id);
    User updateUser(UserDto userDto) throws UserNotFound, NotContainRequiredData, DuplicatedUser;
    User changeUserPassword(UserDto userDto) throws UserNotFound;
    List<OrderDto> getOrderHistory(long userid) throws UserNotFound;
    UserDto addProductToCart(long productId, UserDto userDto) throws UserNotFound, ProductNotFound, InvalidUser;

    UserDto removeProductFromCart(UserDto userDto, long productId) throws UserNotFound, ProductNotFound;
}
