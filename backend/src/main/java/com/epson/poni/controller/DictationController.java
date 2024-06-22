package com.epson.poni.controller;

import com.epson.poni.dto.dictation.*;
import com.epson.poni.service.Dictation.DictationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public List<DifficultyIncorrectResponseDto> difficultIncorrect(@RequestBody DifficultyIncorrectRequestDto difficultyIncorrectRequestDto){
        return dictationService.difficultyIncorrect(difficultyIncorrectRequestDto);
    }

    @GetMapping("/")
    public DifficultyGradingResponseDto scoreResult(Authentication authentication){
        return dictationService.scoreResult(authentication);
    }
}
