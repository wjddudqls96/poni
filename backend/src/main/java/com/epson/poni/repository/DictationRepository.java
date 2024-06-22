package com.epson.poni.repository;

import com.epson.poni.model.Dictation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DictationRepository extends JpaRepository<Dictation, Long> {
    Optional<List<Dictation>> findByUserId(Long userId);
    @Transactional
    void deleteByUserId(Long userId);
}
