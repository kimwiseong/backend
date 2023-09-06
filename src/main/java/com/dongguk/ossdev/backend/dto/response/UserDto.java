package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Getter
@Slf4j
public class UserDto {
    private final String name;

    @Builder
    public UserDto(String name) {
        this.name = name;
    }

    public static UserDto createUserDto(User user) {
        return new UserDto(user.getName());

    }


}
