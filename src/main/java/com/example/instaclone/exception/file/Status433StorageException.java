package com.example.instaclone.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Status433StorageException extends RuntimeException {

    public Status433StorageException(String message) {
        super(message);
    }

    public Status433StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
