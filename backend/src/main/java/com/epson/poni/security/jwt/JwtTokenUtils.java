package com.epson.poni.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.epson.poni.model.User;
import com.epson.poni.repository.UserRepository;

import com.epson.poni.security.UserDetailsImpl;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenUtils {

    private final UserRepository userRepository;

    @Value("${spring.auth.secret.key}")
    String JWT_SECRET;

    private int SEC = 1;
    private int MINUTE = SEC * 60;
    private int HOUR = MINUTE * 60;
    private int DAY = 24 * HOUR;

    private int ACCESS_TOKEN_VALID_MILLI_SEC = 12 * HOUR * 1000;
    private int REFRESH_TOKEN_VALID_MILLI_SEC = 60 * DAY * 1000;


    final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    final String CLAIM_USER_NAME = "USER_NAME";
    final String ACCESS_TOKEN = "ACCESS_TOKEN";
    final String REFRESH_TOKEN = "REFRESH_TOKEN";

    public String generateJwtToken(UserDetailsImpl userDetails){
        try {

            String accessToken = JWT.create()
                    .withIssuer("static/test")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_MILLI_SEC))
                    .sign(Algorithm.HMAC256(JWT_SECRET));

            User user = userRepository.findByUserName(userDetails.getUsername());
            userRepository.save(user);

            return accessToken;
        }
        catch (Exception e){
            throw new IllegalArgumentException("ERROR CREATE JWT TOKEN");
        }
    }

    public String reissuanceAccessToken(String username){
        try {
            String accessToken = JWT.create()
                    .withIssuer("static/test")
                    .withClaim(CLAIM_USER_NAME, username)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_MILLI_SEC))
                    .sign(Algorithm.HMAC256(JWT_SECRET));

            return accessToken;
        }
        catch (Exception e){
            throw new IllegalArgumentException("ERROR CREATE REISSUANCE ACCESS TOKEN");
        }

    }

    public String reissuanceRefreshToken(String username){
        try {
            String refreshToken = JWT.create()
                    .withIssuer("static/test")
                    .withClaim(CLAIM_USER_NAME, username)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_MILLI_SEC))
                    .sign(Algorithm.HMAC256(JWT_SECRET));

            return refreshToken;
        }
        catch (Exception e){
            throw new IllegalArgumentException("ERROR CREATE REISSUANCE ACCESS TOKEN");
        }

    }

}
