package com.epson.poni.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class BlankOptionDto {

    private int count;
    private String type;
    @JsonProperty("isTranslation")
    private boolean translation;
}
