package com.example.instaclone.exception;

public class Status409UserAlreadyRegistered extends RuntimeException {
    public Status409UserAlreadyRegistered(String message) {
        super(message);
    }

}
