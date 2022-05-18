package com.example.timeregistration.security.exception;

public class Status409UserAlreadyRegistered extends RuntimeException {

    private static final int STATUS = 409;

    public int getStatus() {
        return STATUS;
    }

    public Status409UserAlreadyRegistered() {
        super();
    }

    public Status409UserAlreadyRegistered(String message) {
        super(message);
    }

}
