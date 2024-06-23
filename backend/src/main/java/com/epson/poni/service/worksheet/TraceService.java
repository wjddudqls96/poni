package com.epson.poni.service.worksheet;

import com.epson.poni.dto.cart.TraceOptionDto;
import com.epson.poni.utils.KoreanAnalyzer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TraceService {

    private final KoreanAnalyzer koreanAnalyzer;

    public Mono<TraceOptionDto> getTraceData(TraceOptionDto traceOption) {
        return Mono.fromSupplier(() -> traceOption);
    }
}
