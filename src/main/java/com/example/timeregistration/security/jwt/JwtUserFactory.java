package com.example.timeregistration.security.jwt;

import com.example.timeregistration.model.User;

/**
 * Implementation of Factory Method for class {@link JwtUser}.
 *
 * @author Bohdan Chui
 * @version 1.0
 */

public final class JwtUserFactory {

    public JwtUserFactory() {
        // object creates with builder pattern
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities()
        );
    }

}
