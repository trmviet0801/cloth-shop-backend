package com.example.shopbackend.controller;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.exception.UserNotFound;
import com.example.shopbackend.model.User;
import com.example.shopbackend.service.UserService;
import com.example.shopbackend.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
            throws NotContainRequiredData, DuplicatedUser {
        User savedUser = userService.checkDuplicateSaveUser(userDto);
        UserDto result = Convert.UserToDto(savedUser);
        return ResponseEntity.ok()
                .body(result);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws UserNotFound,
            NotContainRequiredData, DuplicatedUser {
        Optional<User> user = userService.findByUsername(userDto.getUsername());
        if (user.isPresent()) {
            User savedUser = userService.updateUser(userDto);
            return ResponseEntity
                    .ok()
                    .body(Convert.UserToDto(savedUser));
        }
        throw new UserNotFound("User not found");
    }
    @PutMapping("password")
    public ResponseEntity<UserDto> changePassword(@RequestBody UserDto userDto) throws UserNotFound {
        User user = userService.changeUserPassword(userDto);
        return ResponseEntity
                .ok()
                .body(Convert.UserToDto(user));
    }
}
