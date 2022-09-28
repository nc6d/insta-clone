package com.example.instaclone.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Status432InvalidFileException extends Exception {

    public Status432InvalidFileException(String message) {
        super(message);
    }

    public Status432InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
