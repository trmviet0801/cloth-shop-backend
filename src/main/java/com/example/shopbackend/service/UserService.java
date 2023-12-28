package com.example.shopbackend.service;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.model.User;

public interface UserService {
    User saveUser(UserDto user);
}
