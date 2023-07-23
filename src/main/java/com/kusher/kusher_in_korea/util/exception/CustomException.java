package com.kusher.kusher_in_korea.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;

    @Override
    public String getMessage() {
        return responseCode.getMessage();
    }
}