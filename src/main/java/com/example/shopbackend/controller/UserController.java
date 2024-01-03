package com.example.shopbackend.controller;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.exception.UserNotFound;
import com.example.shopbackend.model.User;
import com.example.shopbackend.service.UserService;
import com.example.shopbackend.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws NotContainRequiredData, DuplicatedUser {
        User savedUser = userService.saveUser(userDto);
        UserDto result = Convert.UserToDto(savedUser);
        result.setPassword(null);
        return ResponseEntity.ok()
                .body(result);
    }
    @PutMapping("password")
    public ResponseEntity<UserDto> changePassword(@RequestBody UserDto userDto)
            throws UserNotFound {
        Optional<User> user = userService.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
           UserDto userDTO = Convert.UserToDto(user.get());
           userDTO.setPassword(userDto.getPassword());
           User updatedUser = userService.updateUser(userDTO);
           return ResponseEntity.ok()
                   .body(Convert.UserToDto(updatedUser));
        }
        throw new UserNotFound("User Not Found");
    }
}
