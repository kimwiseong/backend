package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.User;
import com.dongguk.ossdev.backend.dto.response.JwtResponseDto;
import com.dongguk.ossdev.backend.repository.UserRepository;
import com.dongguk.ossdev.backend.security.jwt.JwtProvider;
import com.dongguk.ossdev.backend.security.jwt.JwtToken;
import com.dongguk.ossdev.backend.type.LoginProvider;
import com.dongguk.ossdev.backend.type.UserRole;
import com.dongguk.ossdev.backend.util.Oauth2Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        switch (loginProviderType) {
            case KAKAO -> {
                accessToken = oauth2Util.getKakaoAccessToken(authorizationCode);
                socialId = oauth2Util.getKakaoUserInformation(accessToken);
            }
            case GOOGLE -> {
//                accessToken = oauth2Util.getGoogleAccessToken(authorizationCode);
//                socialId = oauth2Util.getGoogleUserInformation(accessToken);
            }
            case NAVER -> {
                accessToken = oauth2Util.getNaverAccessToken(authorizationCode);
                log.info("access token = {}", accessToken);
                socialId = oauth2Util.getNaverUserInformation(accessToken);
            }
        }

        // User Data 존재 여부 확인
        if (socialId == null) { throw new RuntimeException("NOT_FOUND_USER"); }

        // 랜덤 닉네임 생성
        Random random = new Random();
        String userName = loginProviderType.toString() + "-";
        for (int i = 0; i < 3; i++) {
            userName += String.format("%04d", random.nextInt(1000));
        }

        // User 탐색
        Optional<User> user = userRepository.findBySocialIdAndProvider(socialId, loginProviderType);
        User loginUser = null;

        // 기존 유저가 아니라면 새로운 Data 저장, 기존 유저라면 Load
        if (user.isEmpty()) {
            loginUser = userRepository.save(User.builder()
                    .socialId(socialId)
                    .name(userName)
                    .provider(loginProviderType)
                    .role(UserRole.USER)
                    .build());
        } else {
            loginUser = user.get();
        }

        JwtToken jwtToken  = jwtProvider.createTotalToken(loginUser.getId(), loginUser.getRole());
        loginUser.setRefreshToken(jwtToken.getRefresh_token());
        loginUser.setIsLogin(true);


        return JwtResponseDto.builder()
                .jwt(jwtToken)
                .build();
    }
}