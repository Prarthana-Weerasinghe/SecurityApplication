package com.example.security.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data

@Entity
public class User {
    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Role",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID")
    })
   private Set<Role> role;

}