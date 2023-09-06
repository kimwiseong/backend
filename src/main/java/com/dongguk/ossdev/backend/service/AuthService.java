package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.User;
import com.dongguk.ossdev.backend.dto.response.JwtResponseDto;
import com.dongguk.ossdev.backend.repository.UserRepository;
import com.dongguk.ossdev.backend.security.jwt.JwtProvider;
import com.dongguk.ossdev.backend.security.jwt.JwtToken;
import com.dongguk.ossdev.backend.domain.type.LoginProvider;
import com.dongguk.ossdev.backend.domain.type.UserRole;
import com.dongguk.ossdev.backend.util.Oauth2Util;
import com.dongguk.ossdev.backend.util.Oauth2UserInfo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final Oauth2Util oauth2Util;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;


    public String getRedirectUrl(LoginProvider provider) {
        String url = "";
        switch (provider) {
            case KAKAO -> {
                url = oauth2Util.getKakaoRedirectUrl();
            }
            case NAVER -> {
                url = oauth2Util.getNaverRedirectUrl();
            }
            default -> {
                assert true;
            }
        }

        return url;
    }


    @Transactional
    public JwtResponseDto login(String authorizationCode, LoginProvider loginProviderType) {
        String accessToken = null;
        String socialId = null;
        String socialName = null;
        String socialEmail = null;
        switch (loginProviderType) {
            case KAKAO -> {
                accessToken = oauth2Util.getKakaoAccessToken(authorizationCode);
                Oauth2UserInfo oauth2UserInfo = oauth2Util.getKakaoUserInformation(accessToken);
                socialId = oauth2UserInfo.getSocialId();
                socialName = oauth2UserInfo.getSocialName();
                socialEmail = oauth2UserInfo.getSocialEmail();
            }

            case NAVER -> {
                accessToken = oauth2Util.getNaverAccessToken(authorizationCode);
                Oauth2UserInfo oauth2UserInfo = oauth2Util.getNaverUserInformation(accessToken);
                socialId = oauth2UserInfo.getSocialId();
                socialName = oauth2UserInfo.getSocialName();
                socialEmail = oauth2UserInfo.getSocialEmail();
            }
        }

        // User Data 존재 여부 확인
        if (socialId == null) { throw new RuntimeException("NOT_FOUND_USER"); }

        // TODO 닉네임
        if (socialName == null) {
            Random random = new Random();

            socialName = loginProviderType.toString() + "-";
            for (int i = 0; i < 3; i++) {
                socialName += String.format("%04d", random.nextInt(1000));
            }
        }

        // User 탐색
        Optional<User> user = userRepository.findBySocialIdAndProvider(socialId, loginProviderType);
        User loginUser = null;

        // 기존 유저가 아니라면 새로운 Data 저장, 기존 유저라면 Load
        if (user.isEmpty()) {
            loginUser = userRepository.save(User.builder()
                    .socialId(socialId)
                    .name(socialName)
                    .email(socialEmail)
                    .provider(loginProviderType)
                    .role(UserRole.USER)
                    .build());
        } else {
            loginUser = user.get();
        }

        JwtToken jwtToken  = jwtProvider.createTotalToken(loginUser.getId(), loginUser.getRole());
        loginUser.setRefreshToken(jwtToken.getRefreshToken());
        loginUser.setIsLogin(true);

        return JwtResponseDto.builder()
                .jwt(jwtToken)
                .build();
    }

    public void sendRedirectToFront(HttpServletResponse response, JwtToken jwtToken) throws IOException {
        // TODO front url 변경
        final String FRONT_URL = "http://localhost:3000/main";

        final Cookie refreshTokenSecureCookie = new Cookie("refreshToken", jwtToken.getRefreshToken());
        refreshTokenSecureCookie.setPath("/");
        refreshTokenSecureCookie.setHttpOnly(true);
        refreshTokenSecureCookie.setSecure(true);
        refreshTokenSecureCookie.setMaxAge(3600);

        final Cookie accessTokenCookie = new Cookie("accessToken", jwtToken.getAccessToken());

        accessTokenCookie.setPath("/");
        response.addCookie(refreshTokenSecureCookie);
        response.addCookie(accessTokenCookie);
        response.sendRedirect(FRONT_URL);
    }
}