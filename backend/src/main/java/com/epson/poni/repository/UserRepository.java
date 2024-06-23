package com.epson.poni.repository;

import com.epson.poni.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    Optional<User> findByEmail(String email); // 중복 가입 확인
}
