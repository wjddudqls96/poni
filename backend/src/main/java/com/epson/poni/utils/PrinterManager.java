package com.epson.poni.utils;

import com.epson.poni.dto.print.EpsonTokenDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
}
