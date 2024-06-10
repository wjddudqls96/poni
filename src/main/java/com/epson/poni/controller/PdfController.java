package com.epson.poni.controller;

import com.epson.poni.service.HtmlPdfService;
import java.io.FileNotFoundException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pdf")
@RequiredArgsConstructor
public class PdfController {
    private final HtmlPdfService htmlPdfService;

    private static final String pdfDestination = "./output.pdf";

    @PostMapping("/convert")
    public void convertHtmlToPdf() throws IOException {
        htmlPdfService.createPdfFromHtml(pdfDestination);
    }
}
