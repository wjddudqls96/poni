package com.epson.poni.controller;

import com.epson.poni.dto.dictation.DifficultySettingsRequestDto;
import com.epson.poni.dto.dictation.DifficultySettingsResponseDto;
import com.epson.poni.service.DictationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dictation")
@RequiredArgsConstructor
public class DictationController {

    private final DictationService dictationService;

    @PostMapping("/difficulty")
    public DifficultySettingsResponseDto difficultySettings(@RequestBody DifficultySettingsRequestDto difficultySettingsRequestDto){
        DifficultySettingsResponseDto difficultySettingsResponseDto
                = dictationService.difficultySettings(difficultySettingsRequestDto);

        return difficultySettingsResponseDto;
        
    }
}
