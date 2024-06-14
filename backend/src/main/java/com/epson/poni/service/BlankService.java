package com.epson.poni.service;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.cart.BlankType;
import com.epson.poni.utils.GptConnection;
import com.epson.poni.utils.KoreanAnalyzer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BlankService {

    private final KoreanAnalyzer koreanAnalyzer;
    private final GptConnection gptConnection;

    // 단어 or 문장의 유형 문제가 존재함.
    public Mono<List<BlankResponseDto>> create(String sentence, int count, BlankType type) {
        List<String> list = null;

        if(type == BlankType.WORD) {
            list = getNounList(sentence, count);
        }
        else if(type == BlankType.SENTENCE) {
            list = getSplitSentenceList(sentence, count);
        }

        if(list == null) {
            throw new IllegalArgumentException("빈칸문제를 위한 단어들 집합이 없습니다.");
        }

        return gptConnection.requestCreateBlankProblems(list.toString(), count);
    }

    // 단어 유형
    private List<String> getNounList(String sentence, int count) {
        List<String> list = koreanAnalyzer.getNouns(sentence);
        return getRandomList(list, count);
    }

    // 문장 유형
    private List<String> getSplitSentenceList(String sentence, int count) {
        List<String> list = Arrays.asList(sentence.split(" "));
        return getRandomList(list, count);
    }

    private List<String> getRandomList(List<String> list, int count) {
        Random random = new Random();
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(list.size());
            String selectedNoun = list.get(randomIndex);
            resultList.add(selectedNoun);
        }

        return resultList;
    }
}
