package com.epson.poni.dto.explanation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class AnalysisItemDto {
    private String word;
    private String grammar;
    private String word_description;
    private List<String> synonyms;
}
