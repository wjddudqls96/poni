package com.epson.poni.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epson.poni.security.jwt.HeaderTokenExtractor;
import com.epson.poni.security.jwt.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final HeaderTokenExtractor extractor;

    @Value("${spring.auth.secret.key}")
    String JWT_SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("필터 돌아가는중");
        String tokenPayLoad = request.getHeader("Authorization");
        String token = extractor.extract(tokenPayLoad, request);
        try {
                // 토큰 검증 메서드
                DecodedJWT jwt = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                        .build()
                        .verify(token);
                String username = jwt.getClaim("username").asString();

                // 토큰의 만료 여부 확인
                Date expiresAt = jwt.getExpiresAt();
                if (expiresAt != null && expiresAt.after(new Date())) {
                // 유효한 토큰
                    setAuthentication(token);
                } else {
                  // 만료된 토큰
                    jwtTokenUtils.reissuanceAccessToken(username);
                }
                } catch (JWTDecodeException | IllegalArgumentException | NullPointerException e) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().print(e.getMessage());
                    // 토큰 디코딩 오류 또는 서명 일치하지 않음
            }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = jwtTokenUtils.getAuthentication(accessToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/".equals(path);
    }

}