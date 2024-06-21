package com.epson.poni.controller;

import com.epson.poni.dto.dictation.DifficultyGradingResponseDto;
import com.epson.poni.dto.dictation.DifficultyIncorrectRequestDto;
import com.epson.poni.dto.dictation.DifficultySettingsRequestDto;
import com.epson.poni.dto.dictation.DifficultySettingsResponseDto;
import com.epson.poni.service.Dictation.DictationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @PostMapping("/grading")
//    public DifficultyGradingResponseDto difficultyGrading(@RequestParam("file") MultipartFile file,
//                                  @RequestParam("serialNumber") String serialNumber){
//        DifficultyGradingResponseDto difficultyGradingResponseDto = dictationService.difficultyGrading(file, serialNumber);
//
//        return difficultyGradingResponseDto;
//    }

    @PostMapping("/incorrect")
    public void difficultIncorrect(@RequestBody DifficultyIncorrectRequestDto difficultyIncorrectRequestDto){
        dictationService.difficultyIncorrect(difficultyIncorrectRequestDto);
    }
}
