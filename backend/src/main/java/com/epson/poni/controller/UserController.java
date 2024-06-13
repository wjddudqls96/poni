package com.epson.poni.controller;

import com.epson.poni.dto.SignUpDto;
import com.epson.poni.model.User;
import com.epson.poni.repository.UserRepository;
import com.epson.poni.security.UserDetailsImpl;
import com.epson.poni.security.jwt.HeaderTokenExtractor;
import com.epson.poni.security.jwt.JwtDecoder;
import com.epson.poni.security.jwt.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/user")
    public void signUpController(@RequestBody SignUpDto.Reqeust reqeust){
        String encodedPassword = passwordEncoder.encode(reqeust.getPassword());
        User user = new User(reqeust.getUserId(), encodedPassword);
        userRepository.save(user);
    }

    @PostMapping("/test")
    public void test(@AuthenticationPrincipal UserDetailsImpl userDetails){
        User user = userDetails.getUser();
        System.out.println(user);
    }
}
