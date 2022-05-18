package com.example.timeregistration.security.exception;

import com.example.timeregistration.exception.ApiError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class SecurityExceptionHandler {
    @ExceptionHandler({Status409UserAlreadyRegistered.class})
    public ResponseEntity<ApiError> handleCustomException(RuntimeException ex) {
        log.error(ex.getClass() + " : " + ex.getMessage());
        ApiError error = new ApiError(HttpStatus.valueOf(((Status409UserAlreadyRegistered)ex).getStatus()));
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(error.getStatus())
                .body(error);

    }

}
