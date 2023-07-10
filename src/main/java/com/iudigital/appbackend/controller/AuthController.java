package com.iudigital.appbackend.controller;

import com.iudigital.appbackend.model.User;
import com.iudigital.appbackend.modules.Login;
import com.iudigital.appbackend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String NOT_FOUND = "NOT FOUND";
    private static final String SUCCESSFULLY = "Process done correctly";
    final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Login login) {

        boolean exist = userService.existUserByName(login.getUsername());
        if (!exist) {
            return NOT_FOUND;
        }

        User user = userService.getUserByName(login.getUsername());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordMatch = passwordEncoder.matches(login.getPassword(), user.getPassword());

        if (!passwordMatch) {
            return NOT_FOUND;
        }

        return SUCCESSFULLY;
    }

    @PostMapping("/register")
    public String register(@RequestBody User userBody) {

    UserController userController = new UserController(userService);

    return userController.createUser(userBody);

    }

}
