package com.example.timeregistration.model;

import com.example.timeregistration.security.model.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;

@Data
public class User {

    @Id
    private BigInteger id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;
}
