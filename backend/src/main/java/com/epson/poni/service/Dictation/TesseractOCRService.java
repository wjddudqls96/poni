package com.epson.poni.service.Dictation;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

@Service
public class TesseractOCRService implements OCRService {
    private final ITesseract tesseract;

    public TesseractOCRService() {
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath("C:/SGR/poni/backend/src/main/resources/model"); // Tesseract data path
        this.tesseract.setLanguage("kor"); // Set language for OCR
    }

    @Override
//    public String[] extractTextFromImage(byte[] imageBytes) {
    public List<String> extractTextFromImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();

        byte[] imageBytes = new byte[0];
        try {
            imageBytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage image = null;
        String extractedText = null;
        try {
            image = ImageIO.read(inputStream);
            extractedText = tesseract.doOCR(image);
        } catch (IOException | TesseractException e) {
            throw new RuntimeException(e);
        }
//        return extractedText.split("\\r?\\n");
        return null;
    }

    @Override
//    public String[] extractTextFromPdf(byte[] pdfBytes) {
    public List<String> extractTextFromPdf(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        byte[] pdfBytes = new byte[0];
        try {
            pdfBytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try (InputStream inputStream = new ByteArrayInputStream(pdfBytes);
             PDDocument document = Loader.loadPDF(pdfBytes))
             //                     PDDocument.load(inputStream))
        {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            StringBuilder result = new StringBuilder();
            for (int page = 0; page < document.getNumberOfPages(); page++) {
                BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300); // Render image at 300 DPI
                result.append(tesseract.doOCR(image));
                result.append("\n\n"); // 페이지 구분을 위해 두 개의 개행 문자를 추가
            }

//            return result.toString().split("\\r?\\n");
            return null;
        } catch (IOException | TesseractException e) {
            throw new RuntimeException(e);
        }
    }
}
