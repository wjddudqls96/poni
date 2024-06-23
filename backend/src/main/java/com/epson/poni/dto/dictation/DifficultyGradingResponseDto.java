package com.epson.poni.dto.dictation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DifficultyGradingResponseDto {
    private Integer problemsCount;
    private Integer correct;
    private Integer incorrect;
    private List<ProblemDto> problem;
    private String username;
}

