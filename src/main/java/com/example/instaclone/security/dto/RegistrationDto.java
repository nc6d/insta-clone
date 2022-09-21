package com.example.instaclone.security.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class RegistrationDto {

    @Pattern(regexp = "^([\\w\\s]){2,50}$", message = "Input valid name(2..50 chars including whitespaces)")
    private String firstName;

    @Pattern(regexp = "^([\\w\\s]){2,50}$", message = "Input valid name(2..50 chars including whitespaces)")
    private String lastName;

    @Pattern(regexp = "(?:^|\\W)@([A-Za-z0-9_](?:(?:[A-Za-z0-9_]|\\.(?!\\.)){0,28}[A-Za-z0-9_])?)",
            message = "Input your username, should start with one @ at the begin")
    private String username;

    @Pattern(regexp = "^\\w{6,50}$", message = "Input password [a-zA-Z0-9_] (2..50 chars)")
    private String password;

}
