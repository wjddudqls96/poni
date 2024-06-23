package com.epson.poni.service;

import com.epson.poni.dto.gpt.GptRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GptService {

    @Value("${openai.secretKey}")
    private String accessKey;

    private final WebClient webClient;

    @Autowired
    public GptService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public void requestGrammarAnalyze(String sentence) {
        GptRequestDto gptRequestDto = GptRequestDto.createDefault(sentence);

        //TODO: Mono Zip 이용해서 문제집 만들때 openAi로 3번을 접속해야되는 응답시간 문제 때문에 비동기 방식으로 처리해야될듯 합니다.

        Mono<String> firstRequest = webClient.post()
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessKey)
                .bodyValue(gptRequestDto)
                .retrieve()
                .bodyToMono(String.class);

        firstRequest.subscribe(System.out::println, Throwable::printStackTrace);
    }
}
