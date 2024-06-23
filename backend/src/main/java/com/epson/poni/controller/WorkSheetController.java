package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.cart.*;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.dto.translate.TranslateRequestDto;
import com.epson.poni.dto.translate.TranslateResultDto;
import com.epson.poni.service.worksheet.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@RestController
@RequestMapping("/api/v1/worksheet/")
@RequiredArgsConstructor
public class WorkSheetController {

    private final TraceService traceService;
    private final ExplanationService explanationService;
    private final BlankService blankService;
    private final TranslateService translateService;
    private final WorksheetSaveService worksheetSaveService;
    private final WorksheetGetService worksheetGetService;

    private static final Logger log = LoggerFactory.getLogger(WorkSheetController.class);

    @PostMapping("/cart")
    public Mono<Response<CombinedResultDto>> createCart(@RequestBody CartOptionRequestDto requestDto) {
        BlankOptionDto blankOptionDto = requestDto.getBlankOption();
        TraceOptionDto traceOptionDto = requestDto.getTraceOption();

        // 1. WebFlux를 이용해서 비동기 방식으로 작동하게 한다.

        System.out.println(blankOptionDto);
        System.out.println(traceOptionDto);

        Mono<List<ExplanationResponseDto>> grammarAnalysis = explanationService.create(requestDto.getContent())
                .doOnError(e -> log.error("Grammar analysis failed", e));
        Mono<TraceOptionDto> traceData = traceService.getTraceData(traceOptionDto)
                .doOnError(e -> log.error("Trace data retrieval failed", e));
        Mono<List<BlankResponseDto>> blankData = blankService.create(requestDto.getContent(), blankOptionDto.getCount(), blankOptionDto.getType())
                .doOnError(e -> log.error("Blank data creation failed", e));

        return Mono.zip(grammarAnalysis, traceData, blankData)
                .map(tuple -> createResponse(tuple, requestDto.getContent()))
                .doOnError(e -> log.error("Error creating cart", e));
    }

    private Response<CombinedResultDto> createResponse(
            Tuple3<List<ExplanationResponseDto>, TraceOptionDto, List<BlankResponseDto>> tuple, String content) {
        return new Response<>(
                "201",
                "카트 데이터가 성공적으로 생성되었습니다.",
                new CombinedResultDto(tuple.getT1(), tuple.getT2(), tuple.getT3(), content)
        );
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

    @PostMapping("/save")
    public Response<String> saveCart(@RequestBody CombinedResultDto combinedResultDto, Authentication authentication){
        worksheetSaveService.saveCart(combinedResultDto,authentication);
        return new Response<>("201", "카트 데이터 저장 완료.", "success");
    }

    @GetMapping("/")
    public List<CartListAllResponse> getCart(Authentication authentication){
        return worksheetGetService.getListAll(authentication);
    }

    @PostMapping("/")
    public Response<String> getSelectCart(@RequestBody GetSelectCartRequestDto getSelectCartRequestDto){
        String url = worksheetGetService.getList(getSelectCartRequestDto);
        return new Response<>("200", "정상적으로 PDF가 만들어졌습니다.", url);
    }
}
