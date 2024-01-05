package com.example.shopbackend.service.imp;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.NotContainRequiredData;
import com.example.shopbackend.model.User;
import com.example.shopbackend.repository.UserRepository;
import com.example.shopbackend.service.UserService;
import com.example.shopbackend.util.Convert;
import com.example.shopbackend.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) throws NotContainRequiredData, DuplicatedUser {
        if (userDto.getUsername().isEmpty() || userDto.getPassword().isEmpty()
        || userDto.getEmail().isEmpty() || userDto.getPhoneNumber().isEmpty()) {
            throw new NotContainRequiredData("Do not contain required data to create new user");
        } else {
            User user = Convert.DtoToUser(userDto);
            user.setPassword("{bcrypt}" + encoder().encode(user.getPassword()));
            checkDuplicatedUser(user);
            user.setRole(Role.USER.name());
            return userRepository.save(user);
        }
    }

    public void checkDuplicatedUser(User user) throws DuplicatedUser {
        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        if (findByUsername(username).isPresent()) {
            throw new DuplicatedUser("Duplicated username");
        }
        if (findByEmail(email).isPresent()) {
            throw new DuplicatedUser("Duplicated email");
        }
        if (findByPhoneNumber(phoneNumber).isPresent()) {
            throw new DuplicatedUser("Duplicated phone number");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User updateUser(UserDto userDto) {
        User user = Convert.DtoToUser(userDto);
        user.setPassword("{bcrypt}" + encoder().encode(user.getPassword()));
        user.setRole(Role.USER.name());
        return userRepository.save(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
