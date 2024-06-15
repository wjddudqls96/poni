package com.epson.poni.dto.dictation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DifficultySettingsRequestDto {
    private String difficulty;
    private Integer count;
}
