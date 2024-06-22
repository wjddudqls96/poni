package com.epson.poni.service.Dictation;

import com.epson.poni.dto.dictation.*;
import com.epson.poni.model.Dictation;
import com.epson.poni.model.User.User;
import com.epson.poni.model.dictation.Difficulty;
import com.epson.poni.repository.DictationRepository;
import com.epson.poni.repository.DifficultyRepository;
import com.epson.poni.service.print.ScanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class DictationService {
    private final DifficultyRepository difficultyRepository;
    private final DictationRepository dictationRepository;

    // 1-1. tesseract 사용
//    @Autowired
//    private final OCRService ocrService = new TesseractOCRService();
    //1-2. naver-clova 사용
    @Autowired
    private final OCRService ocrService = new ClovaOCRService();


    /**
     * 흐름
     * 1. 사용자가 난이도와 횟수를 설저한다.
     * 2. 사용자가 설정한 값에 따라 사전에 넣어둔 받아쓰기 문장이 출력된다.
     * 3. 해당 문장들을 체점 전에 임시 저장해둔다.
     */
    public DifficultySettingsResponseDto difficultySettings(DifficultySettingsRequestDto difficultySettingsRequestDto) {
        List<Difficulty> byDifficultyEqual = difficultyRepository.findByDifficultyEqual(difficultySettingsRequestDto.getDifficulty(), difficultySettingsRequestDto.getCount());

        //임시 저장
        List<Dictation> dictationList = new ArrayList<>();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<ContentDto> contentDtos = new ArrayList<>();

        for (Difficulty difficulty : byDifficultyEqual) {
            ContentDto contentDto = new ContentDto(difficulty.getId(), difficulty.getContent());
            contentDtos.add(contentDto);

            //임시 저장
            Dictation dictation = new Dictation();
            dictation.setDictation(user.getId(),difficulty.getContent(),difficulty.getId());
            dictationList.add(dictation);
        }

        DifficultySettingsResponseDto difficultySettingsResponseDto = new DifficultySettingsResponseDto();
        difficultySettingsResponseDto.setContentList(contentDtos);


        Optional<List<Dictation>> byUserId = dictationRepository.findByUserId(user.getId());
        if (byUserId.isPresent()){
            dictationRepository.deleteByUserId(user.getId());
        }
        //임시저장
        dictationRepository.saveAll(dictationList);

        return difficultySettingsResponseDto;
    }


    /**
     * 흐름
     * 1. OCR을 통해 이미지 or pdf에서 글자를 추출한다.
     * 2. 추출한 문장과 정답 문장을 비교하여 정답 여부를 체크한다.
     * 3. 정답 여부를 모아 리턴한다.
     */
    public DifficultyGradingResponseDto difficultyGrading(MultipartFile file) {
        //1. OCR을 통해 이미지 or pdf에서 글자를 추출(extractedTextArray)한다.

        List<String> extractedTextArray; // OCR 글자 추출 텍스트
        extractedTextArray = extractText(file);
        for (String s : extractedTextArray) {
            log.info(s);
        }

        // 2. 추출한 문장(extractedTextArray)과 정답 문장을 비교하여 정답 여부를 체크한다.
        return checkForCorrectAnswer(extractedTextArray);
    }

    private DifficultyGradingResponseDto checkForCorrectAnswer(List<String> extractedTextArray) {
        Integer correct = 0;
        Integer incorrect = 0;
        DifficultyGradingResponseDto difficultyGradingResponseDto = new DifficultyGradingResponseDto();

        Long userId = Long.parseLong(extractedTextArray.get(0));
        extractedTextArray.remove(0);
        Optional<List<Dictation>> dictation = dictationRepository.findByUserId(userId);

        Integer contentListIndex = 0;
        List<Ploblem> ploblemList = new ArrayList<>();
        for (String s : extractedTextArray) {
            if (s.isEmpty()) {continue;} //줄 띄어쓰기 생략

            Dictation contentDto = dictation.get().get(contentListIndex);
            if (s.equals(contentDto.getContent())) correct++;
            else incorrect++;

            ploblemList.add(new Ploblem(contentDto.getDifficultyId(),contentDto.getContent(),s));

            contentListIndex++;

        }
        difficultyGradingResponseDto.setCorrect(correct);
        difficultyGradingResponseDto.setIncorrect(incorrect);
        difficultyGradingResponseDto.setProblemsCount(correct + incorrect);
        difficultyGradingResponseDto.setPloblem(ploblemList);

        return difficultyGradingResponseDto;
    }


    private List<String> extractText(MultipartFile file){
          return ocrService.extractTextFromImage(file);
    }



    public List<DifficultyIncorrectResponseDto> difficultyIncorrect(DifficultyIncorrectRequestDto difficultyIncorrectRequestDto) {
        List<String> contents = difficultyIncorrectRequestDto.getIdList();
        List<DifficultyIncorrectResponseDto> incorrectList = new ArrayList<>();
        for (String content : contents) {
            DifficultyIncorrectResponseDto dto = new DifficultyIncorrectResponseDto();
            dto.setBlurry(true);
            dto.setGrid(true);
            dto.setCount(3); // 사용자 설정으로 변경시 변경
            dto.setContent(content);

            incorrectList.add(dto);
        }

        return incorrectList;
    }
}
