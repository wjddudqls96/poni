package com.epson.poni.utils;

import java.util.List;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KoreanAnalyzer {

    private final Komoran komoran;

    public void analyze(String korean) {
        KomoranResult analyzeResultList = komoran.analyze(korean);
        System.out.println(analyzeResultList.getPlainText());

        List<Token> tokenList = analyzeResultList.getTokenList();
        for (Token token : tokenList) {
            System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
        }

        // 명사들 집합
        System.out.println(analyzeResultList.getNouns());
    }

    public List<String> getNouns(String korean) {
        KomoranResult analyzeResultList = komoran.analyze(korean);
        return analyzeResultList.getNouns();
    }
}
