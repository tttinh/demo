package com.example.demo.common;

import lombok.RequiredArgsConstructor;

/**
 * ErrorCode represents all possible response codes returned by this service.
 */
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("Your request is too bad :)!"),
    EXISTED_ACCOUNT("This account already existed!"),
    INVALID_EMAIL_PASSWORD("Invalid email or password!"),
    INVALID_DEPARTMENT_ID("Invalid department ID!"),
    INVALID_DOCTOR_ID("Invalid doctor ID!"),
    SERVER_ERROR("Internal Server Error!");

    private final String message;

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }

}
