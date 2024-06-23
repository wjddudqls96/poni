package com.epson.poni.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class WorksheetPrintRequestDto {
    private String s3url;
    private String print_quality; // 높음 - "high", 낮음 - "normal"
    private String color_mode; // 칼라 - "color", 흑백 - "mono"
    private String sided; // 단면 - "none", 양면 - "long"
    private Boolean reverse_order; // print from the last page - "true", print from first page = "false"
}
