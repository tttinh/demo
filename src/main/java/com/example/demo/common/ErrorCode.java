package com.example.demo.common;

import lombok.RequiredArgsConstructor;

/**
 * ErrorCode represents all possible response codes returned by this service.
 */
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_ID("Your request id is not valid!"),
    INVALID_DEPARTMENT_ID("The department ID is not valid!"),
    INVALID_DOCTOR_ID("The doctor ID is not valid!"),
    SERVER_ERROR("Internal Server Error!");

    private final String message;

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }

}
