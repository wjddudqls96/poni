package com.epson.poni.dto.trace;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceWordResponseDto {
    private List<String> contents;
}
