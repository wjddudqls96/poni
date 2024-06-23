package com.epson.poni.controller;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.service.worksheet.BlankService;
import com.epson.poni.utils.GptConnection;
import com.epson.poni.utils.KoreanAnalyzer;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class testController {

    private final KoreanAnalyzer koreanAnalyzer;
    private final BlankService blankService;
    private final GptConnection gptConnection;

    @GetMapping("/test")
    public void test(){

    }

    @GetMapping("/test/gpt")
    public void test2(){
        gptConnection.requestGrammarAnalyze("나는 최고의 개발자다.");
    }

    @GetMapping("/test/gpt4o")
    public void test3() {
        String[] strs = {"우리는", "맛있다."};
        Mono<List<BlankResponseDto>> test = gptConnection.requestCreateBlankProblems(Arrays.toString(strs), 3);
        test.subscribe(System.out::println);
    }
}
