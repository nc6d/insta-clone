package com.example.timeregistration.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 * Class represents exception form for response to user
 * */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
class ApiValidationError implements ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
