package com.epson.poni.dto.cart;


import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class GetSelectCartResponse {
    private List<List<ExplanationResponseDto>> explanation;
    private List<TraceOptionDto> traceOption;
    private List<List<BlankResponseDto>> blank;
}
