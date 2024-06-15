package com.epson.poni.dto.blank;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BlankResponseDto {
    private List<ProblemDto> problems;
}
