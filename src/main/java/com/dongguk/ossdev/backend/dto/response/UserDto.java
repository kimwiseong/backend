package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Getter
@Slf4j
public class UserDto {
    private final String name;
    private final String email;
//    private final Timestamp created_at;
    private final Timestamp updated_at;
    private final boolean is_graduate;


    public UserDto(String name, String email, Timestamp updated_at, boolean is_graduate) {
        this.name = name;
        this.email = email;
//        this.created_at = created_at;
        this.updated_at = updated_at;
        this.is_graduate = is_graduate;
    }

    public static UserDto createUserDto(User user) {
        log.info("userDto...{}", user.getName());
        return new UserDto(
                user.getName(),
                user.getEmail(),
                Timestamp.valueOf(user.getUpdatedAt()),
                user.getIsGraduate());
    }


}
