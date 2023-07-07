package com.iudigital.appbackend.controller;

import com.iudigital.appbackend.model.Status;
import com.iudigital.appbackend.model.User;
import com.iudigital.appbackend.service.UserService;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

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

    @PostMapping("")
    public String createUser(@RequestBody User user) {
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public String updateUser(@Validated @PathVariable Long id, @RequestBody User user) {
        if (user.getStatus() == null) {
            user.setStatus(Status.ACTIVE);
        }
        return userService.updateUser(id, user);


    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
