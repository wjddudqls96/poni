package com.epson.poni.security;

import com.epson.poni.security.jwt.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final JwtTokenUtils jwtTokenUtils;

    String ACCESS_TOKEN_HEADER = "Access_Token";
    String REFRESH_TOKEN_HEADER = "Refresh_Token";
    String TOKEN_TYPE = "BEARER";
    ObjectMapper objectMapper = new ObjectMapper();

    public FormLoginSuccessHandler(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, String> tokens = jwtTokenUtils.generateJwtToken(userDetails);

        response.addHeader(ACCESS_TOKEN_HEADER,  TOKEN_TYPE + " " + tokens.get("ACCESS_TOKEN"));
        response.addHeader(REFRESH_TOKEN_HEADER, TOKEN_TYPE + " " + tokens.get("REFRESH_TOKEN"));
        response.setContentType("application/json");
    }
}
