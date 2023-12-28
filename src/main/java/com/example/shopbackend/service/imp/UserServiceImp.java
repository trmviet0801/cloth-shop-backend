package com.example.shopbackend.service.imp;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.model.User;
import com.example.shopbackend.repository.UserRepository;
import com.example.shopbackend.service.UserService;
import com.example.shopbackend.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(UserDto userDto) {
        User user = Convert.DtoToUser(userDto);
        user.setPassword(encoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
