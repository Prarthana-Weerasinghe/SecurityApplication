package com.example.security.controller;

import com.example.security.dto.SignupRequest;
import com.example.security.model.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register-new-user")
        public User registerNewUser(@RequestBody SignupRequest signupRequest){
        return userService.registerNewUser(signupRequest);
    }
    @PostConstruct
    public void initRoleAndUser(){
        userService.initRoleAndUser();

    }
    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "this url is only accessible to admin";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasRole('User')")
    public String forClient(){
        return "this url is only accessible to user";
    }
}
