package com.epson.poni.repository;

import com.epson.poni.model.Problem;
import com.epson.poni.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findByScoreId(Long ScoreId);
}
