package com.example.demo.controller;

import com.example.demo.common.ErrorCode;
import com.example.demo.domain.response.ErrorResponse;
import com.example.demo.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorResponse
    handleException(HttpServletRequest req, Exception ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ErrorCode.SERVER_ERROR.getCode(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody ErrorResponse
    handleBadRequest(HttpServletRequest req, BadRequestException ex) {
        return new ErrorResponse(req.getRequestURL().toString(), ex.getCode(), ex.getMessage());
    }

}