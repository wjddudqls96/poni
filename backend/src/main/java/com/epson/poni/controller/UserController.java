package com.epson.poni.controller;

import com.epson.poni.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



//    @PostMapping("/user")
//    public void signUpController(@RequestBody SignUpDto.Reqeust reqeust){
//        String encodedPassword = passwordEncoder.encode(reqeust.getPassword());
//        User user = new User(reqeust.getUserId(), encodedPassword);
//        userRepository.save(user);
//    }
//
//    @PostMapping("/test")
//    public void test(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        User user = userDetails.getUser();
//        System.out.println(user);
//    }
}
