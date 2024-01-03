package com.example.shopbackend.util;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.model.User;

public class Convert {
    public static User DtoToUser(UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );
        user.setId(userDto.getId());
        return user;
    }
    public static UserDto UserToDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber()
        );
        return userDto;
    }
}
