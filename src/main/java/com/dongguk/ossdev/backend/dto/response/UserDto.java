package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
public class UserDto {
    private final String name;
    private final String email;
    private final Timestamp created_at;
    private final Timestamp updated_at;
    private final boolean is_graduate;

    @Builder
    public UserDto(String name, String email, Timestamp created_at, Timestamp updated_at, boolean is_graduate) {
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_graduate = is_graduate;
    }

    public static UserDto createUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .updated_at(Timestamp.valueOf(user.getCreatedAt()))
                .is_graduate(user.getIsGraduate())
                .build();
    }


}
