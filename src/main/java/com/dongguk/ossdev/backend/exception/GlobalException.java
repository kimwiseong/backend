package com.dongguk.ossdev.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return errorCode.getMessage() + message;
        }
    }
}
