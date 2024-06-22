package com.epson.poni.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class GetSelectCartRequestDto {
    private List<Long> cartIds;
}
