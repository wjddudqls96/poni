//package com.epson.poni.security;
//
//import com.epson.poni.model.User.User;
//import com.epson.poni.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailServiceImpl implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//        User user = userRepository.findByUserName(userId);
//        if(user == null) throw new IllegalArgumentException("해당 유저가 존재하지 않습니다.");
//
//        return new UserDetailsImpl(user);
//    }
//}
