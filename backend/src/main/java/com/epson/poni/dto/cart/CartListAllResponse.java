package com.epson.poni.dto.cart;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class CartListAllResponse {
    private Long cartId;
    private Date date;
    private String sentence;
}
