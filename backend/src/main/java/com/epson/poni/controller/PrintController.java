package com.epson.poni.controller;

import com.epson.poni.dto.Response;
import com.epson.poni.dto.print.RegistPrinter;
import com.epson.poni.service.print.PrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/print")
@RequiredArgsConstructor
public class PrintController {
    private final PrintService printService;

    @PostMapping("/regist")
    public Response registPrinter(@RequestBody RegistPrinter.Request request) {
        printService.regist(request);
        return new Response<>("201", "프린터 등록이 완료되었습니다.");
    }
}
