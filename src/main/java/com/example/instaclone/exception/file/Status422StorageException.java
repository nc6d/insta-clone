package com.example.instaclone.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Status422StorageException extends Exception {

    public Status422StorageException(String message) {
        super(message);
    }

    public Status422StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
