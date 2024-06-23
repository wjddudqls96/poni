package com.epson.poni.dto.dictation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DifficultySettingsResponseDto {
    List<ContentDto> contentList = new ArrayList<>();
}


