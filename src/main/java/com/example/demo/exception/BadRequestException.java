package com.example.demo.exception;

import com.example.demo.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class BadRequestException extends RuntimeException {

    private final String code;
    private final String message;

    public BadRequestException(ErrorCode error) {
        code = error.getCode();
        message = error.getMessage();
    }

    public BadRequestException(ErrorCode error, String customMessage) {
        code = error.getCode();
        message = customMessage;
    }

}