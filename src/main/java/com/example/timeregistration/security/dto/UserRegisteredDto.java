package com.example.timeregistration.security.dto;
import com.example.timeregistration.security.model.Role;
import lombok.Data;

@Data
public class UserRegisteredDto {

    private String firstName;

    private String lastName;

    private String email;

    private Role role;

}
