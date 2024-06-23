package com.epson.poni.service.worksheet;

import com.epson.poni.dto.translate.TranslateResultDto;
import com.epson.poni.utils.GptConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final GptConnection gptConnection;


    public Mono<List<TranslateResultDto>> translate(String originalSentence) {
        return gptConnection.requestTranslateSentence(originalSentence);
    }
}
