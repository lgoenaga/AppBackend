package com.iudigital.appbackend.service;

import com.iudigital.appbackend.model.User;
import com.iudigital.appbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    private static final String SUCCESSFULLY = "Process done correctly";
    private static final String NOT_FOUND = "User not found!";
    private static final String ALREADY_EXISTS = "Information already exists";
    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public String createUser(User user) {

        User newUser = new User();

        newUser.setFull_name(user.getFull_name().toUpperCase());
        newUser.setUsername(user.getUsername().toLowerCase());

        if (userRepository.existsByUsername(user.getUsername())) {
            return ALREADY_EXISTS;
        }

        newUser.setEmail(user.getEmail().toLowerCase());

        if (userRepository.existsByEmail(user.getEmail())) {
            return ALREADY_EXISTS;
        }
        newUser.setPassword(user.getPassword());

        String createDate = new Date().toString();
        String updateDate = new Date().toString();

        newUser.setCreated_at(createDate);
        newUser.setUpdated_at(updateDate);

        userRepository.save(newUser);
        return SUCCESSFULLY;
    }

    public String updateUser(Long id, User user) {

        PasswordEncoder encoder = passwordEncoder();

        User exitUser = userRepository.findById(id).orElse(null);

        if (exitUser == null) {
            return NOT_FOUND;
        }

        if (!Objects.equals(exitUser.getUsername(), user.getUsername())){
            if(userRepository.existsByUsername(user.getUsername())) {
                return ALREADY_EXISTS;
            }
        }

        if (!Objects.equals(exitUser.getEmail(), user.getEmail())) {
            if (userRepository.existsByEmail(user.getEmail())) {
                return ALREADY_EXISTS;
            }
        }

        exitUser.setFull_name(user.getFull_name().toUpperCase());
        exitUser.setUsername(user.getUsername().toLowerCase());
        exitUser.setEmail(user.getEmail().toLowerCase());

        exitUser.setPassword(encoder.encode(user.getPassword()));

        String updateDate = new Date().toString();

        exitUser.setUpdated_at(updateDate);

        userRepository.save(exitUser);
        return SUCCESSFULLY;
    }

    public String deleteUser(Long id) {

        User exitUser = userRepository.findById(id).orElse(null);

        if (exitUser == null) {
            return NOT_FOUND;
        }

        userRepository.deleteById(id);
        return SUCCESSFULLY;
    }

}
