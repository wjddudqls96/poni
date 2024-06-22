package com.epson.poni.repository;

import com.epson.poni.model.Score;
import com.epson.poni.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Optional<Score> findAllByUser(User user);
}
