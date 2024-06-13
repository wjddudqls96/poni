package com.epson.poni.service;

import com.epson.poni.dto.dictation.ContentDto;
import com.epson.poni.dto.dictation.DifficultySettingsRequestDto;
import com.epson.poni.dto.dictation.DifficultySettingsResponseDto;
import com.epson.poni.model.dictation.Difficulty;
import com.epson.poni.repository.DifficultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DictationService {
    private final DifficultyRepository difficultyRepository;

    public DifficultySettingsResponseDto difficultySettings(DifficultySettingsRequestDto difficultySettingsRequestDto){
        List<Difficulty> byDifficultyEqual = difficultyRepository.findByDifficultyEqual(difficultySettingsRequestDto.getDifficulty(),difficultySettingsRequestDto.getCount());

        List<ContentDto> contentDtos = new ArrayList<>();
        for (Difficulty difficulty : byDifficultyEqual) {
            ContentDto contentDto = new ContentDto(difficulty.getId(), difficulty.getContent());
            contentDtos.add(contentDto);
        }

        DifficultySettingsResponseDto difficultySettingsResponseDto = new DifficultySettingsResponseDto();
        difficultySettingsResponseDto.setContentList(contentDtos);

        return difficultySettingsResponseDto;
    }


}
