package com.epson.poni.dto.translate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateResultDto {
    String sentence;
    String translatedSentence;
    String pronunciation;
}
