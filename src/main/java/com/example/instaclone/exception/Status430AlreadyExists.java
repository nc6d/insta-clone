package com.example.instaclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Status430AlreadyExists extends Exception {
    public Status430AlreadyExists(String message) {
        super(message);
    }
}
