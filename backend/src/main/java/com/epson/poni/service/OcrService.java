package com.epson.poni.service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.Loader;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

@Service
public class OcrService {
    private final ITesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath("C:/SGR/poni/backend/src/main/resources/model"); // Tesseract data path
        this.tesseract.setLanguage("kor"); // Set language for OCR
    }

    public String[] extractTextFromImage(byte[] imageBytes) throws TesseractException, IOException {
        InputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(inputStream);
        String extractedText = tesseract.doOCR(image);
        return extractedText.split("\\r?\\n");
    }

    public String[] extractTextFromPdf(byte[] pdfBytes) throws IOException, TesseractException {
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

            return result.toString().split("\\r?\\n");
        }
    }
}
