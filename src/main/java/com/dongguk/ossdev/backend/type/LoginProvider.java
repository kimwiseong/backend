package com.dongguk.ossdev.backend.type;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
public enum LoginProvider {
    KAKAO("KAKAO"),
    GOOGLE("GOOGLE"),
    NAVER("NAVER");

    private final String provider;

    @Override
    public String toString() {
        return provider;
    }
}
