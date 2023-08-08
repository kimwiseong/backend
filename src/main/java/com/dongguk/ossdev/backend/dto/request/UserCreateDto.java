package com.dongguk.ossdev.backend.dto.request;

import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String name;
    private String email;
    private String password;
    private Timestamp createdDate;
    private boolean isGraduate;

    @Builder
    public UserCreateDto(String name, String email, String password, Timestamp createdDate, boolean isGraduate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.isGraduate = isGraduate;
    }
}
