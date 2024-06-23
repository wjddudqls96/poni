package com.epson.poni.dto.cart;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlankHtmlDto {
    private List<String> contents;
    private String content_en;
    private String answer;
}
