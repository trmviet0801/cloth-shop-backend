package com.example.shopbackend.service.imp;

import com.example.shopbackend.dto.UserDto;
import com.example.shopbackend.exception.DuplicatedUser;
import com.example.shopbackend.exception.UserNotFound;
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
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkDuplicateSaveUser(UserDto userDto) throws DuplicatedUser {
        User user = Convert.DtoToUser(userDto);
        user.encryptPassword();
        checkDuplicatedUser(user);
        user.setRole(Role.USER.name());
        return userRepository.save(user);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public User encryptPassword(User user) {
        user.setPassword("{bcrypt}" + encoder().encode(user.getPassword()));
        return user;
    }


    public void checkDuplicatedUser(User user) throws DuplicatedUser {
        String username = user.getUsername();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();
        long id = user.getId();

        Optional<User> userFindByUsername = findByUsername(username);
        Optional<User> userFindByEmail = findByEmail(email);
        Optional<User> userFindByPhoneNumber = findByPhoneNumber(phoneNumber);

        if (userFindByUsername.isPresent()) {
            if (id != userFindByUsername.get().getId()) {
                throw new DuplicatedUser("Duplicated username");
            }
        }
        if (userFindByEmail.isPresent()) {
            if (id != userFindByEmail.get().getId()) {
                throw new DuplicatedUser("Duplicated email");
            }
        }
        if (userFindByPhoneNumber.isPresent()) {
            if (id != userFindByPhoneNumber.get().getId()) {
                throw new DuplicatedUser("Duplicated phone number");
            }
        }
    }

    @Override
    public User updateUser(UserDto userDto) throws UserNotFound, DuplicatedUser {
        User user = Convert.DtoToUser(userDto);
        Optional<User> databaseUser = findByUsername(user.getUsername());
        if (databaseUser.isPresent()) {
            user.setPassword(databaseUser.get().getPassword());
            checkDuplicatedUser(user);
            return userRepository.save(user);
        }
        throw new UserNotFound("User not found");
    }

    @Override
    public User changeUserPassword(UserDto userDto) throws UserNotFound{
        Optional<User> userFromDatabase = findById(userDto.getId());
        if (userFromDatabase.isPresent() && isEqualWithDatabaseUser(userDto)) {
            String newPass = userDto.getPassword();
            User currentUser = userFromDatabase.get();
            currentUser.setPassword(newPass);
            currentUser.encryptPassword();
            userRepository.save(currentUser);
            return currentUser;
        }
        throw new UserNotFound("User not found");
    }

    public boolean isEqualWithDatabaseUser(UserDto userDto) {
        Optional<User> databaseUser = userRepository.findByUsername(userDto.getUsername());
        if (databaseUser.isPresent()) {
            String username = userDto.getUsername();
            String databaseUsername = databaseUser.get().getUsername();
            long userId = userDto.getId();
            long databaseUserId = databaseUser.get().getId();
            String phoneNumber = userDto.getPhoneNumber();
            String databasePhoneNumber = databaseUser.get().getPhoneNumber();

            return username.equals(databaseUsername) &&
                    userId == databaseUserId &&
                    phoneNumber.equals(databasePhoneNumber);
        } else {
            return false;
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
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }
}
