package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Getter
public class UserDto {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final Timestamp createdDate;
    private final boolean isGraduate;

}
