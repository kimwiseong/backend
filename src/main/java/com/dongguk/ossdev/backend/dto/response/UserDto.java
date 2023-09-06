package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Getter
@Slf4j
public class UserDto {
    private final String name;
    private final String email;
    private final Timestamp updatedAt;
    private final boolean isGraduate;


    public UserDto(String name, String email, Timestamp updated_at, boolean is_graduate) {
        this.name = name;
        this.email = email;
        this.updatedAt = updated_at;
        this.isGraduate = is_graduate;
    }

    public static UserDto createUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                Timestamp.valueOf(user.getUpdatedAt()),
                user.getIsGraduate());
    }


}
