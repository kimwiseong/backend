package com.dongguk.ossdev.backend.security;


import com.dongguk.ossdev.backend.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    final private JwtProvider jwtProvider;
    final private CustomUserDetailService userDetailService;

    private final String[] urls = { "/favicon.ico",
            "/auth/kakao", "/auth/kakao/callback",
            "/auth/naver", "/auth/naver/callback"};


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtAuthenticationFilter Request doFilterInternal");

        String token = JwtProvider.refineToken(request);
        Claims claims = jwtProvider.validateToken(token);

        String userid = claims.get("id").toString();

        CustomUserDetail userDetails = (CustomUserDetail) userDetailService.loadUserByUsername(userid);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        filterChain.doFilter(request, response);
        log.info("JwtAuthenticationFilter Response doFilterInternal");
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Arrays.stream(urls).filter(url -> url.equals(request.getRequestURI())).count() > 0;
    }
}
