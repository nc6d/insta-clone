package com.example.instaclone.exception.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No have content")
public class Status404UserNotFoundException extends Exception {

    public Status404UserNotFoundException(String msg) {
        super(msg);
    }
}