package com.dongguk.ossdev.backend.dto.response;

import com.dongguk.ossdev.backend.security.jwt.JwtToken;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDto {
    private JwtToken jwt;
}
