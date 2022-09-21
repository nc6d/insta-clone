package com.example.instaclone.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Status412InvalidFileException extends Exception {

    public Status412InvalidFileException(String message) {
        super(message);
    }

    public Status412InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
