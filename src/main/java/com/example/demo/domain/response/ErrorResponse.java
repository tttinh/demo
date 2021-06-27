package com.example.demo.domain.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private final String url;
    private final String code;
    private final String message;

}
