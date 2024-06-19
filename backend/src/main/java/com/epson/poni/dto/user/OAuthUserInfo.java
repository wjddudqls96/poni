package com.epson.poni.dto.user;

import com.epson.poni.model.User.Role;
import com.epson.poni.model.User.User;
import lombok.Builder;

import java.util.Map;

@Builder
public record OAuthUserInfo (
        String name,
        String email
){
    public User toEntity() {
        return User.builder()
                .userName(name)
                .email(email)
                .role(Role.USER)
                .build();
    }

    public static OAuthUserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuthUserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .build();
    }

}
