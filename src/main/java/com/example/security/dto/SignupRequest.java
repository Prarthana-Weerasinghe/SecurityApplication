package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignupRequest {
    private String username;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userRole;

    private String myRole;
    private String h;

}
