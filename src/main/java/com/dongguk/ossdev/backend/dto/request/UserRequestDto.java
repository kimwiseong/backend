package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.domain.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    private final String name;
    private final String email;
    private final boolean isGraduate;

    public boolean getIsGraduate() {
        return this.isGraduate;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .isGraduate(isGraduate)
                .build();
    }
}
