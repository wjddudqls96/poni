package com.epson.poni.dto.dictation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
    Long difficulty_id;
//    String difficulty;
    String content;
}
