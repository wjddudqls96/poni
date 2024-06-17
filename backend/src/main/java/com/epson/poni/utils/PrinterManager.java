package com.epson.poni.utils;

import com.epson.poni.dto.print.EpsonTokenDto;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PrinterManager {
    @Value("${epson.client.id}")
    private String clientId;

    @Value("${epson.secretKey}")
    private String secret;

    @Value("${epson.deviceId}")
    private String devideId;

    @Value("${server.url}")
    String serverUrl;


    //    public String getToken(String devideId) {
    // 소셜로그인 완성되면 토큰에서 deviceId 가져오는 방식으로 바꾸기
    public EpsonTokenDto getToken() {

        String url = "https://api.epsonconnect.com/api/1/printing/oauth2/auth/token?subject=printer&grant_type=password&username=";
        String username = "xri7063s9g2p35@print.epsonconnect.com";
        String password = "yourPassword";

        String auth = clientId + ":" + secret;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.set("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        url += devideId + "&password=";

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EpsonTokenDto> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                EpsonTokenDto.class
        );
        return response.getBody();
    }

    public void registDestination() {
        String aliasName = "poni";
        String type = "url";
        String destination = serverUrl + "/api/scan/load";

        EpsonTokenDto epsonTokenDto = getToken();

        String accessToken = epsonTokenDto.getAccess_token();
        String subjectId = epsonTokenDto.getSubject_id();

        String url = "https://api.epsonconnect.com/api/1/scanning/scanners/" + subjectId + "/destinations";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type","application/json; charset=UTF-8");

        Map<String, String> body = new HashMap<>();
        body.put("alias_name", aliasName);
        body.put("type", type);
        body.put("destination", destination);

        HttpEntity<Map<String, String>> requestBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestBody,
            String.class
        );
    }

    public void print() {

    }

    public void upload(File content) {
        String jobId = "8aaa0b1f217245429bee37e5914fb0cb";
        String baseUri = "https://www.epsonconnect.com/c33fe124ef80c3b13670be27a6b0bcd7/v1/storage/PostData?Key=2babc9a51a2c52b88bae92436d75676430203504703236422165c8b359f602eeb9bcdd90859787dc";
        String localFilePath = "C:/dev/Section.jpeg";

        String ext = localFilePath.substring(localFilePath.lastIndexOf('.'));
        String fileName = "1" + ext;
        String uploadUri = baseUri + "&File=" + fileName;

        File file = new File(localFilePath);
        long fileLength = file.length();
        String errStr = "";
        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(fileLength);
            headers.setContentType(MediaType.IMAGE_JPEG);

            FileSystemResource fileResource = new FileSystemResource(file);
            HttpEntity<FileSystemResource> requestEntity = new HttpEntity<>(fileResource, headers);

            ResponseEntity<String> response = restTemplate.exchange(uploadUri, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println(response);
                System.out.println("File uploaded successfully.");
            } else {
                System.out.println("Failed to upload file: " + response.getStatusCode() + " " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
