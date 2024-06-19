package com.epson.poni.service.user;

import com.epson.poni.dto.user.UserInfoUpdateRequestDto;
import com.epson.poni.model.User.User;
import com.epson.poni.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public void userInfoUpdate(UserInfoUpdateRequestDto userInfoUpdateRequestDto, Authentication authentication) {
        User user = (User)authentication.getPrincipal();

        user.Change(userInfoUpdateRequestDto.getLanguage(), userInfoUpdateRequestDto.getDeviceId());
        userRepository.save(user);
    }
}
