package com.epson.poni.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CartOptionRequestDto {
    private TraceOptionDto traceOption;
    private BlankOptionDto blankOption;
    private boolean explanation;
    private String content;
}

