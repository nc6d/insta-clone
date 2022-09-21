package com.example.instaclone.security.jwt;

import com.example.instaclone.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(User user) {
        return new JwtUser(user.getId(), user.getFirstName(),
                user.getLastName(), user.getUsername(), user.getPassword()
        );
    }

}
