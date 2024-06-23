package com.epson.poni.service.user;

import com.epson.poni.dto.user.OAuthUserInfo;
import com.epson.poni.model.User.User;
import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.PrincipalDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. 유저 정보(attributes) 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        // 2. resistrationId 가져오기 (third-party id)
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 3. userNameAttributeName 가져오기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // 4. 유저 정보 dto 생성
        OAuthUserInfo oAuthUserInfo = OAuthUserInfo.ofGoogle(oAuth2UserAttributes);

        // 5. 회원가입 및 로그인
        User user = getOrSave(oAuthUserInfo);

        // 6. OAuth2User로 반환
        return new PrincipalDetails(user, oAuth2UserAttributes, userNameAttributeName);
    }

    private User getOrSave(OAuthUserInfo oAuthUserInfo) {

        User user = userRepository.findByEmail(oAuthUserInfo.email())
                .orElseGet(oAuthUserInfo::toEntity);
        return userRepository.save(user);
    }
}
