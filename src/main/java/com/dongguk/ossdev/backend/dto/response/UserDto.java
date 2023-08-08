package com.dongguk.ossdev.backend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Timestamp createdDate;
    private boolean isGraduate;

    @Builder
    public UserDto(Long id, String name, String email, String password, Timestamp createdDate, boolean isGraduate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.isGraduate = isGraduate;
    }
}
