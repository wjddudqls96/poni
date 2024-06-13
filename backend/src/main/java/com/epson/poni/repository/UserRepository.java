package com.epson.poni.repository;

import com.epson.poni.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
