package com.dongguk.ossdev.backend.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class UserCreateDto {
    private final String name;
    private final String email;
    private final String password;
    private final Timestamp createdDate;
    private final boolean isGraduate;
}
