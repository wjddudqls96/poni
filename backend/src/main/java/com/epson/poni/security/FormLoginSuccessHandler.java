package com.epson.poni.security;

import com.epson.poni.dto.user.UserInfoDto;
import com.epson.poni.model.User.User;
import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FormLoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRepository userRepository;

    String ACCESS_TOKEN_HEADER = "Authorization";
    String TOKEN_TYPE = "BEARER";
    private static final String URI = "http://localhost:5173/main?accessToken=";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        String AccessToken = jwtTokenUtils.generateAccessToken(userDetails);
//        jwtTokenUtils.generateRefreshToken(userDetails,AccessToken);

        response.addHeader(ACCESS_TOKEN_HEADER,  TOKEN_TYPE + " " + AccessToken);
        response.setContentType("application/json");

        response.sendRedirect(URI + TOKEN_TYPE + " " + AccessToken);

        response.setCharacterEncoding("UTF-8");

        // 필요한 필드만 포함된 JSON 형식으로 변환
        Optional<User> user = userRepository.findByEmail(userDetails.getEmail());
        UserInfoDto userInfoDto;
        if(user.isPresent()){
            userInfoDto = UserInfoDto.builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .role(user.get().getRole())
                    .deviceId(user.get().getDeviceId())
                    .language(user.get().getLanguage())
                    .userName(user.get().getUserName())
                    .build();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(userInfoDto);

            // JSON 응답 작성
            response.getWriter().write(jsonResponse);
        }else {
            // 사용자 정보를 찾지 못한 경우 처리
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("User not found");
        }

        response.getWriter().flush();
    }
}
