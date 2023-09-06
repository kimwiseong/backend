package com.dongguk.ossdev.backend.util;

import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class Oauth2Util {
    // kakao
    @Value("${client.provider.kakao.authorization-uri}")
    private String KAKAO_AUTHORIZATION_URL;

    @Value("${client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URL;

    @Value("${client.provider.kakao.user-info-uri}")
    private String KAKAO_USERINFO_URL;

    @Value("${client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    //naver
    @Value("${client.provider.naver.authorization-uri}")
    private String NAVER_AUTHORIZATION_URL;

    @Value("${client.provider.naver.token-uri}")
    private String NAVER_TOKEN_URL;

    @Value("${client.provider.naver.user-info-uri}")
    private String NAVER_USERINFO_URL;

    @Value("${client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Value("${client.registration.naver.redirect-uri}")
    private String NAVER_REDIRECT_URL;


    private final RestTemplate restTemplate = new RestTemplate();

    // kakao
    public String getKakaoRedirectUrl() {
        return KAKAO_AUTHORIZATION_URL
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public String getNaverRedirectUrl() {
        return NAVER_AUTHORIZATION_URL
                + "?client_id=" + NAVER_CLIENT_ID
                + "&redirect_uri=" + NAVER_REDIRECT_URL
                + "&response_type=code";
    }

    public String getKakaoAccessToken(String authCode) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("client_secret", KAKAO_CLIENT_SECRET);
        params.add("redirect_uri", KAKAO_REDIRECT_URL);
        params.add("code", authCode);

        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_TOKEN_URL,
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        return JsonParser.parseString(Objects.requireNonNull(response.getBody()))
                .getAsJsonObject().get("access_token").getAsString();
    }

    public Oauth2UserInfo getKakaoUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer "+ accessToken);
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> kakaoProfileRequest= new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_USERINFO_URL,
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        JsonElement element = JsonParser.parseString(response.getBody());

        return Oauth2UserInfo.builder()
                .socialId(element.getAsJsonObject().get("id").getAsString())
                .socialName(element.getAsJsonObject().getAsJsonObject("properties").get("nickname").getAsString())
                .socialEmail(element.getAsJsonObject().getAsJsonObject("kakao_account").get("email").getAsString())
                .build();
    }

    // naver
    public String getNaverAccessToken(String authCode) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", NAVER_CLIENT_SECRET);
        params.add("redirect_uri", NAVER_REDIRECT_URL);
        params.add("code", authCode);

        HttpEntity<MultiValueMap<String,String>> NaverTokenRequest = new HttpEntity<>(params,httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_TOKEN_URL,
                HttpMethod.POST,
                NaverTokenRequest,
                String.class
        );

        return JsonParser.parseString(Objects.requireNonNull(response.getBody()))
                .getAsJsonObject().get("access_token").getAsString();
    }

    public Oauth2UserInfo getNaverUserInformation(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "Bearer "+ accessToken);
        httpHeaders.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String,String >> NaverProfileRequest= new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(
                NAVER_USERINFO_URL,
                HttpMethod.POST,
                NaverProfileRequest,
                String.class
        );

        JsonElement element = JsonParser.parseString(response.getBody());

        return Oauth2UserInfo.builder()
                .socialId(element.getAsJsonObject().getAsJsonObject("response").get("id").getAsString())
                .socialName(element.getAsJsonObject().getAsJsonObject("response").get("name").getAsString())
                .socialEmail(element.getAsJsonObject().getAsJsonObject("response").get("email").getAsString())
                .build();
    }
}

