package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.exception.ErrorCode;
import com.dongguk.ossdev.backend.exception.ExceptionDto;
import com.dongguk.ossdev.backend.exception.GlobalException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


@Slf4j
@Getter
public class ResponseDto<D> {

    @JsonIgnore
    private final HttpStatus httpStatus;
    private final Boolean success;
    private final D data;
    private final ExceptionDto error;

    @Builder
    private ResponseDto(HttpStatus httpStatus, Boolean success,
                        @Nullable D data, ExceptionDto error) {
        this.httpStatus = httpStatus;
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ResponseDto<T> ok(@Nullable T data) {
        return new ResponseDto<>(HttpStatus.OK, true, data, null);
    }

    public static <T> ResponseDto<T> created(@Nullable T data) {
        return new ResponseDto<>(HttpStatus.CREATED, true, data, null);
    }

    public static ResponseDto<Object> toResponseEntity(Exception e) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, false, null, new ExceptionDto(ErrorCode.SERVER_ERROR));
    }

    public static ResponseDto<Object> toResponseEntity(GlobalException e) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, false, null, new ExceptionDto(e.getErrorCode()));
    }
}
