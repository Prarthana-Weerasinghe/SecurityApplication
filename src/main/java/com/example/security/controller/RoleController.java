package com.example.security.controller;

import com.example.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.security.service.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin

public class RoleController{
    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }

}
