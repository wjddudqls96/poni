package com.epson.poni.repository;

import com.epson.poni.dto.dictation.ContentDto;
import com.epson.poni.dto.dictation.DifficultySettingsResponseDto;
import com.epson.poni.model.dictation.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
    @Query("SELECT d FROM Difficulty d WHERE d.difficulty = :difficulty ORDER BY RAND() LIMIT :count")
    List<Difficulty> findByDifficultyEqual(@Param("difficulty") String difficulty,@Param("count") Integer count);

}
