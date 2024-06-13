package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.dto.cart.CartOptionRequestDto;
import com.epson.poni.dto.cart.CombinedResultDto;
import com.epson.poni.dto.cart.TraceOptionDto;
import com.epson.poni.dto.explanation.ExplanationRequestDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.service.ExplanationService;
import com.epson.poni.service.TraceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/worksheet/")
@RequiredArgsConstructor
public class WorkSheetController {

    private final TraceService traceService;
    private final ExplanationService explanationService;

    @PostMapping("/cart")
    public Mono<Response<CombinedResultDto>> createCart(@RequestBody CartOptionRequestDto requestDto) {

        // 1. WebFlux를 이용해서 비동기 방식으로 작동하게 한다.
        Mono<List<ExplanationResponseDto>> grammarAnalysis = explanationService.create(requestDto.getContent());
        Mono<TraceOptionDto> traceData = traceService.getTraceData(requestDto.getTraceOption());

        return Mono.zip(grammarAnalysis, traceData,
                (grammarResult, traceOption) -> new Response<>("201", "카트 데이터가 성공적으로 생성되었습니다.", new CombinedResultDto(grammarResult, traceOption)));
    }

    /*
    1. 용어설명은 옵션이 없다.
    2. chatGPT API에 content를 넘겨줘서 데이터를 넘겨준다.
    * */
    @PostMapping("/explanation")
    public void getExplanation(@RequestBody ExplanationRequestDto requestDto) {

    }
}
