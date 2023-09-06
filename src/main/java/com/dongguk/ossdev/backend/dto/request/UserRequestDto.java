package com.dongguk.ossdev.backend.dto.request;

import com.dongguk.ossdev.backend.domain.Reading;
import com.dongguk.ossdev.backend.domain.User;
import lombok.*;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class UserRequestDto {
    private final String name;


    public User toEntity() {
        return User.builder()
                .name(name)
                .build();
    }
}
