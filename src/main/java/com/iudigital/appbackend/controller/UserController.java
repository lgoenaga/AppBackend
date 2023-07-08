package com.iudigital.appbackend.controller;

import com.iudigital.appbackend.model.User;
import com.iudigital.appbackend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final String NOT_FOUND = "NOT FOUND";
    private static final String BAD_REQUEST = "BAD REQUEST";
    private static final String ALREADY_EXISTS = "Information already exists";


    final
    UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById( @PathVariable Long id){
        return  userService.getUserById(id);
    }

    @PostMapping("")
    public String createUser(@RequestBody User userBody) {
        try{

            boolean exist = userService.existUserNameOrEmail(userBody.getUsername(), userBody.getEmail());

            if (exist){
                return ALREADY_EXISTS;
            }
            User createUser = new User();

            createUser.setRole(userService.role(userBody.getRole()));
            createUser.setStatus(userService.status(userBody.getStatus()));

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String generatedPassword = passwordEncoder.encode(userBody.getPassword());

            createUser.setFull_name(userBody.getFull_name().toUpperCase());
            createUser.setUsername(userBody.getUsername().toLowerCase());
            createUser.setEmail(userBody.getEmail());
            createUser.setPassword(generatedPassword);
            createUser.setCreated_at(new Date().toString());
            createUser.setUpdated_at(new Date().toString());

            return userService.createUser(createUser);

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }




    }

    @PutMapping("/{id}")
    public String updateUser(@Validated @PathVariable Long id, @RequestBody User userBody)  {
        try{

            boolean existUser = userService.existUserById(id);

            if (!existUser){
                return NOT_FOUND;
            }

            User updateUser = userService.getUserById(id);

            if (!Objects.equals(updateUser.getUsername(), userBody.getUsername()) && (userService.existUserByName(userBody.getUsername()))) {
                    return ALREADY_EXISTS;
                }

            if (!Objects.equals(updateUser.getEmail(), userBody.getEmail()) && (userService.existUserByEmail(userBody.getEmail()))) {
                    return ALREADY_EXISTS;
            }

            updateUser.setRole(userService.role(userBody.getRole()));
            updateUser.setStatus(userService.status(userBody.getStatus()));

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String generatedPassword = passwordEncoder.encode(userBody.getPassword());

            String updateDate = new Date().toString();

            updateUser.setFull_name(userBody.getFull_name().toUpperCase());
            updateUser.setUsername(userBody.getUsername().toLowerCase());
            updateUser.setEmail(userBody.getEmail().toLowerCase());

            updateUser.setPassword(generatedPassword);
            updateUser.setUpdated_at(updateDate);

            return userService.createUser(updateUser);

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)  {
        try{
            boolean existUser = userService.existUserById(id);
            if (existUser){
                return userService.deleteUser(id);
            }else{
                return NOT_FOUND;
            }

        }catch (Exception e){
            throw new IllegalArgumentException(BAD_REQUEST + e.getMessage());
        }

    }

}
