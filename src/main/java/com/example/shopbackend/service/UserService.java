package com.example.shopbackend.service;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.exception.UserNotFound;
import com.example.shopbackend.model.User;

import java.util.Optional;

public interface UserService {
    User checkDuplicateSaveUser(UserDto user) throws NotContainRequiredData, DuplicatedUser;
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String PhoneNumber);
    Optional<User> findById(long id);
    User updateUser(UserDto userDto) throws UserNotFound, NotContainRequiredData, DuplicatedUser;
    User changePassword(UserDto userDto) throws UserNotFound;
}
