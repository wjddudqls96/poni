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
    private List<AnalysisItem> analysis;

}

@Data
@ToString
@NoArgsConstructor
class AnalysisItem {
    private String word;
    private String grammar;
    private String word_description;
    private List<String> synonyms;
}
