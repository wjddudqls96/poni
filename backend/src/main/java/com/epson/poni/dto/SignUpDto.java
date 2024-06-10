package com.epson.poni.dto;

import lombok.Data;

public class SignUpDto {

    @Data
    public static class Reqeust{
        private String userId;
        private String password;
    }
}
