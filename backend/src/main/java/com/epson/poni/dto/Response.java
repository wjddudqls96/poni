package com.epson.poni.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String code;
    private String message;
    private T data;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    };
}
