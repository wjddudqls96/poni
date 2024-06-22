package com.epson.poni.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TraceHtmlDto {
    @JsonProperty("isBlurry")
    private boolean blurry;

    @JsonProperty("isGrid")
    private boolean grid;

    private int count;

    private List<List<Character>> content;
}
