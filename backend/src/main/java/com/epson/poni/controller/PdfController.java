package com.epson.poni.controller;

import com.epson.poni.service.HtmlPdfService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {
    private final HtmlPdfService htmlPdfService;

//    private static final String pdfDestination = "./output.pdf";

    @PostMapping("/convert")
    public void convertHtmlToPdf() {
        Map<String, Object> map = new HashMap<>();

        // HTML에 넣을 변수들 지정
//        map.put("title", "Welcome to Our Website");
//        map.put("message1", "머라하노 ㅋㅋ");
//        map.put("message2", "엡손엡손엡손이다.");

        htmlPdfService.createAndUploadPdf(map);
    }
}
