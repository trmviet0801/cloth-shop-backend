package com.example.shopbackend.util;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.model.User;

public class Convert {
    public static User DtoToUser(UserDto userDto) throws NotContainRequiredData {
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
        return user;
    }
    public static UserDto UserToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }
}
