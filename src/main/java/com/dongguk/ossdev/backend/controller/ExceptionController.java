package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponseDto illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return  ErrorResponseDto.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto runtimeExceptionHandler(RuntimeException e) {
        return  ErrorResponseDto.builder()
                .message(e.getMessage())
                .build();
    }
}
