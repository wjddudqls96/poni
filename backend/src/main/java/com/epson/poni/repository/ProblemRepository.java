package com.epson.poni.repository;

import com.epson.poni.model.Problem;
import com.epson.poni.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
