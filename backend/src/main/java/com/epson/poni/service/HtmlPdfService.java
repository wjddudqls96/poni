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
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Service
@RequiredArgsConstructor
public class HtmlPdfService {

    private final S3Uploader s3Uploader;

    // PDF를 만들고 S3에 올리는 서비스로직.
    public void createAndUploadPdf()  {
        String htmlFilePath = "pdf_layout/template.html";
        ByteArrayOutputStream pdfStream = convertHtmlToPdf(htmlFilePath);
        String fileName = UUID.randomUUID().toString() + ".pdf";
        String fileUrl = s3Uploader.UploadFileUsingStream(fileName, pdfStream);
        System.out.println(fileUrl);
    }

    // HTML 파일을 PDF로 변환하는 메소드
    public ByteArrayOutputStream convertHtmlToPdf(String htmlFilePath) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // TODO: Page 크기를 지정할 수 있다. -> 추후에 다른 크기를 만들때 고치면된다.
        try (PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf, PageSize.A4)) {

            document.setMargins(50, 50, 50, 50);
            ConverterProperties properties = new ConverterProperties();
            String html = loadHtmlFileAsString(htmlFilePath);
            HtmlConverter.convertToPdf(html, pdf, properties);
        } catch (IOException e) {
            throw new BaseException(ErrorCode.PDF_GENERATION_FAILED);
        }

        return outputStream;
    }

    // HTML 파일을 문자열로 로드하는 메서드.
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
