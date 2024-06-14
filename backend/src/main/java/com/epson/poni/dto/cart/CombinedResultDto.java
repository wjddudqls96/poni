package com.epson.poni.dto.cart;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CombinedResultDto {
    private List<ExplanationResponseDto> explanation;
    private TraceOptionDto traceOption;
    private List<BlankResponseDto> blank;
}
