package com.epson.poni.dto.dictation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@NoArgsConstructor
@Data
public class DifficultyIncorrectResponseDto {
    @JsonProperty("isBlurry")
    private boolean blurry;

    @JsonProperty("isGrid")
    private boolean grid;

    private int count;
    private String content;
}
