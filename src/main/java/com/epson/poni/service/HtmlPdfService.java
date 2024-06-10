package com.epson.poni.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
public class HtmlPdfService {

    public void createPdfFromHtml(String pdfDestination) throws IOException {
        PdfWriter writer = new PdfWriter(pdfDestination);
        PdfDocument pdf = new PdfDocument(writer);

        // TODO: Page 크기를 지정할 수 있다. -> 추후에 다른 크기를 만들때 고치면된다.
        Document document = new Document(pdf, PageSize.A4);

        // TODO: Margin 설정 단위는 Point이다.
        document.setMargins(50, 50, 50, 50);

        ConverterProperties properties = new ConverterProperties();
        String html = loadHtmlFileAsString("pdf_layout/template.html");
        HtmlConverter.convertToPdf(html, pdf, properties);
    }

    public String loadHtmlFileAsString(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);

        try(InputStream inputStream = resource.getInputStream()) {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            return new String(bdata, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            throw new IOException("HTML file 불러오기 실패", e);
        }
    }
}
