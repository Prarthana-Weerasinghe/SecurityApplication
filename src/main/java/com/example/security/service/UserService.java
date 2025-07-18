package com.example.security.service;

import com.example.security.dto.SignupRequest;
import com.example.security.model.User;
import com.example.security.model.Role;
import com.example.security.repo.UserRepo;
import com.example.security.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(SignupRequest signupRequest) {
        if (!userRepo.existsById(signupRequest.getUsername())) {
            User user = new User();
            user.setUserName(signupRequest.getUsername());
            user.setUserFirstName(signupRequest.getUserFirstName());
            user.setUserLastName(signupRequest.getUserLastName());
            user.setUserPassword(getEncodedPassword(signupRequest.getUserPassword()));

            Set<Role> adminRoles = new HashSet<>();
            if(signupRequest.getUserRole().equalsIgnoreCase("User")){
                Role role = new Role();
                role.setRoleName((signupRequest.getUserRole()));
                adminRoles.add(role);
            }else{
                throw  new RuntimeException("No role matching..");
            }
            user.setRole(adminRoles);
            return userRepo.save(user);
        }
        throw new RuntimeException("Already Registered");
    }

    public void initRoleAndUser() {
        Role adminRole = new Role();
        Role userRole = new Role();
        if (!roleRepo.existsById("Admin")) {
            adminRole.setRoleName("Role_Admin");
            adminRole.setRoleDescription("Admin role");
            roleRepo.save(adminRole);
        }
        if (!roleRepo.existsById("User")) {
            userRole.setRoleName("User");
            userRole.setRoleDescription("User role");
            roleRepo.save(userRole);
        }
        if (!userRepo.existsById("admin123")) {
            User user = new User();
            user.setUserName("admin123");
            user.setUserFirstName("Prarthana");
            user.setUserLastName("Nethmini");
            user.setUserPassword(getEncodedPassword("admin@123"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            user.setRole(adminRoles);
            userRepo.save(user);
        }
        if (!userRepo.existsById("user123")) {
            User user = new User();
            user.setUserName("user123");
            user.setUserFirstName("Kim");
            user.setUserLastName("Taehyung");
            user.setUserPassword(getEncodedPassword("user@123"));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);

            user.setRole(userRoles);
            userRepo.save(user);
        }
    }
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);

    }
}
