package com.example.timeregistration.service;

import com.example.timeregistration.security.model.Role;
import com.example.timeregistration.model.User;

import java.util.List;

public interface UserService {

    User register(User user, Role role);

    List<User> getAll();

    User findByEmail(String email);

}
