package com.blazemhan.demosecurity.controller;

import com.blazemhan.demosecurity.entity.User;
import com.blazemhan.demosecurity.repository.UserRepository;
import com.blazemhan.demosecurity.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //class constructor with its dependencies injected
    public AuthController(UserRepository userRepository, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // registers a new user
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

    // function to sign in a user
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }
}
