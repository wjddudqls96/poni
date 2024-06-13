package com.epson.poni.controller;

import com.epson.poni.service.GptService;
import com.epson.poni.utils.KoreanAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class testController {

    private final KoreanAnalyzer koreanAnalyzer;
    private final GptService gptService;

    @GetMapping("/test")
    public void test(){
        koreanAnalyzer.analyze("대한민국은 민주공화국이다.");
    }

    @GetMapping("/test/gpt")
    public void test2(){
        gptService.requestGrammarAnalyze("나는 최고의 개발자다.");
    }
}
