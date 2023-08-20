package com.dongguk.ossdev.backend.security.jwt;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JwtToken {
    private String accessToken;
    private String refreshToken;
}
