package com.epson.poni.security.provider;

import com.epson.poni.model.User;
import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.UserDetailsImpl;
import com.epson.poni.security.jwt.JwtDecoder;
import com.epson.poni.security.jwt.JwtPreProcessingToken;
import com.epson.poni.security.jwt.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final JwtDecoder jwtDecoder;

    public JWTAuthProvider(UserRepository userRepository,
                           JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.jwtTokenUtils = jwtTokenUtils;
        this.jwtDecoder = new JwtDecoder(jwtTokenUtils);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        String username = jwtDecoder.decodeUsername(token);

        User user = userRepository.findByUserId(username);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtPreProcessingToken.class);
    }
}
