package com.example.instaclone.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
class ApiValidationError implements ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

}
