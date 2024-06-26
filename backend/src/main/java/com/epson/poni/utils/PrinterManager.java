package com.epson.poni.utils;

import com.epson.poni.dto.cart.WorksheetPrintRequestDto;
import com.epson.poni.dto.print.EpsonTokenDto;
import com.epson.poni.dto.print.PrintInfo;
import java.io.File;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

    public void print(String filePath) {
        EpsonTokenDto epsonTokenDto = getToken();
        String jobId = upload(epsonTokenDto, filePath,null,null,null,false);
        String accessToken = epsonTokenDto.getAccess_token();
        String url = "https://api.epsonconnect.com/api/1/printing/printers/" + epsonTokenDto.getSubject_id() + "/jobs/" + jobId + "/print";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type","application/json; charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestEntity,
            void.class
        );
    }

    public void print(String filePath, WorksheetPrintRequestDto worksheetPrintRequestDto) {
        EpsonTokenDto epsonTokenDto = getToken();
        String jobId = upload(epsonTokenDto, filePath,worksheetPrintRequestDto.getPrint_quality(),worksheetPrintRequestDto.getColor_mode(),worksheetPrintRequestDto.getSided(),worksheetPrintRequestDto.getReverse_order());
        String accessToken = epsonTokenDto.getAccess_token();
        String url = "https://api.epsonconnect.com/api/1/printing/printers/" + epsonTokenDto.getSubject_id() + "/jobs/" + jobId + "/print";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type","application/json; charset=UTF-8");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        System.out.println(response);

    }

    public String upload(EpsonTokenDto epsonTokenDto, String filePath, String print_quality, String color_mode, String sided, Boolean reverse_order) {

        PrintInfo printInfo = getPrintInfo(epsonTokenDto.getSubject_id(), epsonTokenDto.getAccess_token(),
                print_quality,
                color_mode,
                sided,
                reverse_order
                );

        String jobId = printInfo.getId();
        String baseUri = printInfo.getUpload_uri();


        String ext = filePath.substring(filePath.lastIndexOf('.'));
        String fileName = "1" + ext;
        String uploadUri = baseUri + "&File=" + fileName;

        RestTemplate restTemplate = new RestTemplate();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            Resource fileResource = new UrlResource(filePath);
            headers.setContentLength(fileResource.contentLength());
            HttpEntity<Resource> requestEntity = new HttpEntity<>(fileResource, headers);

            ResponseEntity<String> response = restTemplate.exchange(uploadUri, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
            } else {
                System.out.println("Failed to upload file: " + response.getStatusCode() + " " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobId;
    }

    public PrintInfo getPrintInfo(String subjectId, String accessToken, String print_quality, String color_mode, String sided, Boolean reverse_order) {

        String url = "https://api.epsonconnect.com/api/1/printing/printers/" + subjectId + "/jobs";
        String jobName = "print";
        String printMode = "photo";

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type","application/json; charset=UTF-8");

        Map<String, Object> body = new HashMap<>();
        body.put("job_name", jobName);
        body.put("print_mode", printMode);
        //프린트 설정 넣기
        if(print_quality != null ){
            body.put("job_name", "sample");
            body.put("print_mode", "document");

//            Map<String, Object> printSetting = new HashMap<>();
//            printSetting.put("media_size", "ms_a4");
//            printSetting.put("media_type", "mt_plainpaper");
//            printSetting.put("borderless", false);
//            printSetting.put("print_quality", print_quality);
//            printSetting.put("source", "auto");
//            printSetting.put("color_mode", color_mode);
//            printSetting.put("2_sided", sided);
//            printSetting.put("reverse_order", reverse_order);
//            printSetting.put("copies", 1);
//            printSetting.put("collate", true);
//
//            body.put("print_setting", printSetting);
        }

        HttpEntity<Map<String, Object>> requestBody = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        PrintInfo printInfo = restTemplate.exchange(
            url,
            HttpMethod.POST,
            requestBody,
            PrintInfo.class
        ).getBody();
        System.out.println(printInfo.getId());
        return printInfo;
    }
}
