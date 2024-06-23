package com.epson.poni.service.worksheet;

import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.utils.GptConnection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExplanationService {
    private final GptConnection gptConnection;

    public Mono<List<ExplanationResponseDto>> create(String content) {
        return gptConnection.requestGrammarAnalyze(content);
    }
}
