package com.example.timeregistration.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.MappingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.naming.ConfigurationException;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
public class SimpleExceptionController {

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class,
            EmptyResultDataAccessException.class, NullPointerException.class, UsernameNotFoundException.class,
            JwtException.class, DateTimeParseException.class})
    public ResponseEntity<ApiError> handle(RuntimeException ex) {
        log.error(ex.getClass() + " : " + ex.getMessage());
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(error.getStatus())
                .body(error);

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({MappingException.class, ConfigurationException.class})
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
        log.error(ex.getMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setMessage(ex.getMessage());
        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuth(RuntimeException ex) {
        log.error(ex.getMessage());
        ApiError error = new ApiError(HttpStatus.FORBIDDEN);
        error.setMessage(ex.getMessage());

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
        error.setSubErrors(ex.getBindingResult().getAllErrors().stream().map(exception -> ApiValidationError
                        .builder()
                        .field(((FieldError) exception).getField())
                        .message(exception.getDefaultMessage())
                        .rejectedValue(((FieldError) exception).getRejectedValue())
                        .object(ex.getObjectName())
                        .build())
                .collect(Collectors.toList()));

        return ResponseEntity
                .status(error.getStatus())
                .body(error);
    }
}
