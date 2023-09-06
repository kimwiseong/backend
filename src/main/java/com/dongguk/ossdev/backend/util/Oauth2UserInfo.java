package com.dongguk.ossdev.backend.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Oauth2UserInfo {

    private String socialId;
    private String socialName;
    private String socialEmail;

    @Builder
    public Oauth2UserInfo(String socialId, String socialName, String socialEmail) {
        this.socialId = socialId;
        this.socialName = socialName;
        this.socialEmail = socialEmail;
    }

}
