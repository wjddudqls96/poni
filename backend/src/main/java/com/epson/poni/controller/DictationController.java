package com.epson.poni.controller;

import com.epson.poni.dto.dictation.DifficultyGradingResponseDto;
import com.epson.poni.dto.dictation.DifficultySettingsRequestDto;
import com.epson.poni.dto.dictation.DifficultySettingsResponseDto;
import com.epson.poni.service.DictationService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    @PostMapping("/grading")
    public DifficultyGradingResponseDto difficultyGrading(@RequestParam("file") MultipartFile file,
                                  @RequestParam("serialNumber") String serialNumber){
        DifficultyGradingResponseDto difficultyGradingResponseDto = dictationService.difficultyGrading(file, serialNumber);

        return difficultyGradingResponseDto;
    }
}
