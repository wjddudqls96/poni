package com.epson.poni.dto.blank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProblemDto {
    private String content_kr;
    private String content_en;
    private String answer;
}
