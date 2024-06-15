package com.epson.poni.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TraceOptionDto {

    @JsonProperty("isBlurry")
    private boolean blurry;

    @JsonProperty("isGrid")
    private boolean grid;

    private int count;
}
