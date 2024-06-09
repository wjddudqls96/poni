package com.epson.poni.repository;

import com.epson.poni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
