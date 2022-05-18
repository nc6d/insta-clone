package com.example.timeregistration.service.impl;

import com.example.timeregistration.security.exception.Status409UserAlreadyRegistered;
import com.example.timeregistration.security.model.Role;
import com.example.timeregistration.model.User;
import com.example.timeregistration.repo.UserRepository;
import com.example.timeregistration.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user, Role role) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(repoUser -> {
                    throw new Status409UserAlreadyRegistered("Email " + user.getEmail() + " is already registered");
                });

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No user with email " + email));
        log.info("IN findByUsername - user: {} found by email: {}", result, email);
        return result;
    }

}
