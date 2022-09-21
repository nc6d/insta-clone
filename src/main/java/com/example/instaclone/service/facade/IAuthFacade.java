package com.example.instaclone.service.facade;

import org.springframework.security.core.Authentication;

public interface IAuthFacade {
    Authentication getAuthentication();
    String getUsername();
}
