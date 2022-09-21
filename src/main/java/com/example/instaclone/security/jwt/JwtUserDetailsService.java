package com.example.instaclone.security.jwt;

import com.example.instaclone.exception.entity.Status404UserNotFoundException;
import com.example.instaclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return JwtUserFactory.create(userService.findByUsername(username));
        } catch (Status404UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
