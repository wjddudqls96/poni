package com.epson.poni.service;

import com.epson.poni.error.ErrorCode;
import com.epson.poni.error.exception.BaseException;
import com.epson.poni.utils.S3Uploader;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.font.FontProvider;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HtmlPdfService {

    private final S3Uploader s3Uploader;
    private final TemplateEngine templateEngine;

    // PDF를 만들고 S3에 올리는 서비스로직.
    public void createAndUploadPdf(Map<String, Object> data)  {
        String templateName = "template";
        ByteArrayOutputStream pdfStream = convertHtmlToPdf(templateName, data);
        String fileName = UUID.randomUUID().toString() + ".pdf";
        String fileUrl = s3Uploader.UploadFileUsingStream(fileName, pdfStream);
        System.out.println(fileUrl);
    }

    // HTML 파일을 PDF로 변환하는 메소드
    public ByteArrayOutputStream convertHtmlToPdf(String templateName, Map<String, Object> data) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf, PageSize.A4)) {

            document.setMargins(0, 0, 0, 0);
            ConverterProperties properties = new ConverterProperties();

            // Thymeleaf를 사용하여 HTML 생성
            Context context = new Context();
            // context에 데이터 집어넣기.
            context.setVariables(data);
            String htmlContent = templateEngine.process(templateName, context);

            // 폰트 설정
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont("src/main/resources/static/fonts/NotoSansKR-VariableFont_wght.ttf");
            fontProvider.addFont("src/main/resources/static/fonts/NotoSansKR-Bold.ttf");
            fontProvider.addFont("src/main/resources/static/fonts/NotoSansKR-Medium.ttf");
            properties.setFontProvider(fontProvider);
            properties.setCharset("utf-8");

            // CSS 포함
            properties.setBaseUri("src/main/resources/templates/");

            HtmlConverter.convertToPdf(htmlContent, pdf, properties);
        } catch (IOException e) {
            throw new BaseException(ErrorCode.PDF_GENERATION_FAILED);
        }

        return outputStream;
    }

    // HTML 파일을 문자열로 로드하는 메서드. (사용하지 않음)
    public String loadHtmlFileAsString(String path) {
        ClassPathResource resource = new ClassPathResource(path);

        try(InputStream inputStream = resource.getInputStream()) {
            byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            return new String(bdata, StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            throw new BaseException(ErrorCode.NOT_FOUNT_HTML_RESOURCE);
        }
    }
}
