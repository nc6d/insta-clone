package com.example.instaclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Status403AlreadyExists extends Exception {
    public Status403AlreadyExists(String message) {
        super(message);
    }
}
