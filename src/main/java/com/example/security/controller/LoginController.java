package com.example.security.controller;

import com.example.security.dto.LoginRequest;
import com.example.security.dto.LoginResponse;
import com.example.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("/authentication")
    public LoginResponse createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest)throws Exception{
        return jwtService.createJwtToken(loginRequest);
    }

}
