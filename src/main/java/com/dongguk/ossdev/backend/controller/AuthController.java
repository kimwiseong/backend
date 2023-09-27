package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.annotation.UserId;
import com.dongguk.ossdev.backend.security.jwt.JwtToken;
import com.dongguk.ossdev.backend.service.AuthService;
import com.dongguk.ossdev.backend.domain.type.LoginProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/kakao")
    public void getKakaoRedirectURL(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getRedirectUrl(LoginProvider.KAKAO));
    }

    @GetMapping("/kakao/callback")
    public void loginByKakao(HttpServletResponse response,
                                          @RequestParam("code") String authCode) throws IOException {
        JwtToken jwtToken = authService.login(authCode, LoginProvider.KAKAO).getJwt();
        authService.sendRedirectToFront(response, jwtToken);
    }

    @GetMapping("/naver")
    public void getNaverRedirectURL(HttpServletResponse response) throws IOException {
        response.sendRedirect(authService.getRedirectUrl(LoginProvider.NAVER));
    }

    @GetMapping("/naver/callback")
    public void loginByNaver(HttpServletResponse response,
                                          @RequestParam("code") String authCode) throws IOException {
        JwtToken jwtToken = authService.login(authCode, LoginProvider.NAVER).getJwt();
        authService.sendRedirectToFront(response, jwtToken);
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(@UserId Long userId,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        authService.logout(userId);

        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        final Cookie refreshTokenCookie = Arrays.stream(cookies).filter((cookie) -> cookie.getName().equals("refreshToken"))
                .findFirst().orElseThrow(() -> new RuntimeException("유효하지 않은 토큰입니다."));

        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
