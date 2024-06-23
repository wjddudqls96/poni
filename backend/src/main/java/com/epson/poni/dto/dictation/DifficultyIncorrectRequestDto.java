package com.epson.poni.dto.dictation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DifficultyIncorrectRequestDto {
    private List<String> idList = new ArrayList<>();
}
