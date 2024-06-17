package com.epson.poni.dto.print;

import lombok.Data;

public class RegistPrinter {
    @Data
    public static class Request {
        String printerAddress;
    }
}
