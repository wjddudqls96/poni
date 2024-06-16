package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.cart.BlankOptionDto;
import com.epson.poni.dto.cart.CartOptionRequestDto;
import com.epson.poni.dto.cart.CombinedResultDto;
import com.epson.poni.dto.cart.TraceOptionDto;
import com.epson.poni.dto.explanation.ExplanationRequestDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.dto.translate.TranslateRequestDto;
import com.epson.poni.dto.translate.TranslateResultDto;
import com.epson.poni.service.BlankService;
import com.epson.poni.service.ExplanationService;
import com.epson.poni.service.TraceService;
import java.util.List;

import com.epson.poni.service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/worksheet/")
@RequiredArgsConstructor
public class WorkSheetController {

    private final TraceService traceService;
    private final ExplanationService explanationService;
    private final BlankService blankService;
    private final TranslateService translateService;

    @PostMapping("/cart")
    public Mono<Response<CombinedResultDto>> createCart(@RequestBody CartOptionRequestDto requestDto) {
        BlankOptionDto blankOptionDto = requestDto.getBlankOption();
        TraceOptionDto traceOptionDto = requestDto.getTraceOption();

        // 1. WebFlux를 이용해서 비동기 방식으로 작동하게 한다.
        Mono<List<ExplanationResponseDto>> grammarAnalysis = explanationService.create(requestDto.getContent());
        Mono<TraceOptionDto> traceData = traceService.getTraceData(traceOptionDto);
        Mono<List<BlankResponseDto>> blankData = blankService.create(requestDto.getContent(), blankOptionDto.getCount(), blankOptionDto.getType());

        return Mono.zip(grammarAnalysis, traceData, blankData)
                .map(tuple -> new Response<>(
                        "201",
                        "카트 데이터가 성공적으로 생성되었습니다.",
                        new CombinedResultDto(tuple.getT1(), tuple.getT2(), tuple.getT3())
                ));
    }

    /*
    1. 용어설명은 옵션이 없다.
    2. chatGPT API에 content를 넘겨줘서 데이터를 넘겨준다.
    * */
    @PostMapping("/explanation")
    public void getExplanation(@RequestBody ExplanationRequestDto requestDto) {

    }

    @PostMapping("/translate")
    public Mono<Response<TranslateResultDto>> translate(@RequestBody TranslateRequestDto request) {
        return translateService.translate(request.getOriginalSentence())
                .map(translationResults -> {
                    TranslateResultDto resultDto = new TranslateResultDto();
                    resultDto.setTranslatedSentence(translationResults.get(0).getTranslatedSentence()); // 번역된 문장 설정
                    resultDto.setPronunciation(translationResults.get(0).getPronunciation()); // 발음 기호 설정
                    return new Response<>("201", "번역이 완료되었습니다.", resultDto);
                });
    }
}