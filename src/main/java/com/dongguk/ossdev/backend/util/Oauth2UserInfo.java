package com.dongguk.ossdev.backend.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Oauth2UserInfo {

    private String socialId;
    private String socialName;


    @Builder
    public Oauth2UserInfo(String socialId, String socialName) {
        this.socialId = socialId;
        this.socialName = socialName;
    }

}
