package com.epson.poni.service.Dictation;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OCRService {
    List<String> extractTextFromImage(MultipartFile file);
    List<String> extractTextFromPdf(MultipartFile file);

}
