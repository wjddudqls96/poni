package com.epson.poni.service.Dictation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class ClovaOCRService implements OCRService{

    @Value("${naver.clova.ocr.url}")
    private String apiUrl;

    @Value("${naver.clova.ocr.secret}")
    private String secret;

    @Override
    public List<String> extractTextFromImage(MultipartFile file) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("X-OCR-SECRET", secret);

            // JSON 메시지 생성
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("version", "v2");
            requestBody.put("requestId", "guide-demo");
            requestBody.put("timestamp", 156146581);
            requestBody.put("lang", "ko");

            Map<String, String> image = new HashMap<>();
            image.put("format", "jpg");
            image.put("name", "demo");
            requestBody.put("images", new Map[]{image});

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMessage = objectMapper.writeValueAsString(requestBody);

            // Multipart body 구성
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", file.getResource()); // MultipartFile을 추가

            body.add("message", jsonMessage);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

            // JSON Parsing
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode imagesNode = rootNode.path("images").get(0);
            JsonNode fieldsNode = imagesNode.path("fields");

//            String[] extractedTexts = new String[fieldsNode.size()];
//            for (int i = 0; i < fieldsNode.size(); i++) {
//                extractedTexts[i] = fieldsNode.get(i).path("inferText").asText();
//            }

            List<String> extractedTexts = new ArrayList<>();
            StringBuilder currentLine = new StringBuilder();

            for (JsonNode field : fieldsNode) {
                String text = field.path("inferText").asText();
                boolean lineBreak = field.path("lineBreak").asBoolean();

                if (lineBreak) {
                    if (currentLine.length() > 0) {
                        currentLine.append(" ");
                    }
                    currentLine.append(text);
                    extractedTexts.add(currentLine.toString());
                    currentLine.setLength(0);
                } else {
                    if (currentLine.length() > 0) {
                        currentLine.append(" ");
                    }
                    currentLine.append(text);
                }

            }

            if (currentLine.length() > 0) {
                extractedTexts.add(currentLine.toString());
            }

            return extractedTexts;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<String> extractTextFromPdf(MultipartFile file) {
        return null;
    }
}
