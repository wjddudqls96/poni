package com.epson.poni.dto.explanation;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ExplanationResponseDto {
    private String sentence;
    private String speak;
    private List<AnalysisItemDto> analysis;

}

