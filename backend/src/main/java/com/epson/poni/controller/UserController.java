package com.epson.poni.controller;

import com.epson.poni.dto.user.UserInfoUpdateRequestDto;
import com.epson.poni.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/update")
    public void userInfoUpdate(Authentication authentication, @RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto){
        userService.userInfoUpdate(userInfoUpdateRequestDto,authentication);
    }

}
