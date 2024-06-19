package com.epson.poni.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.epson.poni.model.User.User;
import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private static final String KEY_ROLE = "role";

    public String generateJwtToken(PrincipalDetails userDetails, int expire){
        try {

            String accessToken = JWT.create()
                    .withIssuer("static/test")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expire))
                    .sign(Algorithm.HMAC256(JWT_SECRET));


            return accessToken;
        }
        catch (Exception e){
            throw new IllegalArgumentException("ERROR CREATE JWT TOKEN");
        }
    }

    public String generateAccessToken(PrincipalDetails userDetails){
        return generateJwtToken(userDetails,ACCESS_TOKEN_VALID_MILLI_SEC);
    }

    public void generateRefreshToken(PrincipalDetails userDetails, String accessToken) {
        String refreshToken = generateJwtToken(userDetails, REFRESH_TOKEN_VALID_MILLI_SEC);
//        tokenService.saveOrUpdate(authentication.getName(), refreshToken, accessToken); // redis에 저장
    }

    public Authentication getAuthentication(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .withIssuer("static/test")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            List<SimpleGrantedAuthority> authorities = extractAuthorities(decodedJWT);
            String username = decodedJWT.getClaim(CLAIM_USER_NAME).asString();
            User user = userRepository.findByUserName(username);
            return new UsernamePasswordAuthenticationToken(user, token, authorities);

        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Invalid token");
        }
    }

    private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
        Claim rolesClaim = jwt.getClaim(KEY_ROLE);
        if (rolesClaim.isNull()) {
            return Collections.emptyList();
        }
        List<String> roleNames = rolesClaim.asList(String.class);
        return roleNames.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    //유효 검사
//    public boolean validateToken(String token) {
//        if (!StringUtils.hasText(token)) {
//            return false;
//        }
//
//        Claims claims = parseClaims(token);
//        return claims.getExpiration().after(new Date());
//    }
//
//    private Claims parseClaims(String token) {
//        try {
//            return Jwts.parser().verifyWith(secretKey).build()
//                    .parseSignedClaims(token).getPayload();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims();
//        } catch (MalformedJwtException e) {
//            throw new TokenException(INVALID_TOKEN);
//        } catch (SecurityException e) {
//            throw new TokenException(INVALID_JWT_SIGNATURE);
//        }
//    }


    // 3. accessToken 재발급
//    public String reissueAccessToken(String accessToken) {
//        if (StringUtils.hasText(accessToken)) {
//            Token token = tokenService.findByAccessTokenOrThrow(accessToken);
//            String refreshToken = token.getRefreshToken();
//
//            if (validateToken(refreshToken)) {
//                String reissueAccessToken = generateAccessToken(getAuthentication(refreshToken));
//                tokenService.updateToken(reissueAccessToken, token);
//                return reissueAccessToken;
//            }
//        }
//        return null;
//    }

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
