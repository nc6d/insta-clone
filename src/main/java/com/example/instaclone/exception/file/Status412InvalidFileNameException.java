package com.example.instaclone.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Status412InvalidFileNameException extends Exception {

    public Status412InvalidFileNameException(String filename) {
        super("Cannot store file with relative path outside current directory " + filename);
    }

    public Status412InvalidFileNameException(String filename, Throwable cause) {
        super("Failed to store empty file " + filename, cause);
    }
}
