package com.epson.poni.utils;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.dto.gpt.GptRequestDto;
import com.epson.poni.dto.translate.TranslateRequestDto;
import com.epson.poni.dto.translate.TranslateResultDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GptConnection {

    @Value("${openai.secretKey}")
    private String accessKey;

    private final WebClient webClient;

    private final ObjectMapper mapper;

    @Autowired
    public GptConnection(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
        this.mapper = objectMapper;
    }

    public Mono<List<ExplanationResponseDto>> requestGrammarAnalyze(String sentence) {
        GptRequestDto gptRequestDto = GptRequestDto.createDefault(sentence);

        return webClient.post()
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessKey)
                .bodyValue(gptRequestDto)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonStr -> {
                    try {
                        return parseJson(jsonStr, ExplanationResponseDto.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to parse JSON", e);
                    }
                });
    }

    // TODO: 이거 잘못 만지고 계속 호출하면 큰일난다.(비용 문제.) GPT 4o 쓰기때문에 GptRequestDto의 createBlank는 물어보고 사용할 것.
    public Mono<List<BlankResponseDto>> requestCreateBlankProblems(String sentence, int count) {
        GptRequestDto gptRequestDto = GptRequestDto.createBlank(sentence, count);

        return webClient.post()
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessKey)
                .bodyValue(gptRequestDto)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonStr -> {
                    try {
                        return parseJson(jsonStr, BlankResponseDto.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to parse JSON", e);
                    }
                });
    }


    public Mono<List<TranslateResultDto>> requestTranslateSentence(String sentence) {
        GptRequestDto gptRequestDto = GptRequestDto.translateSentence(sentence);

        return webClient.post()
                .uri("/chat/completions")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessKey)
                .bodyValue(gptRequestDto)
                .retrieve()
                .bodyToMono(String.class)
                .map(jsonStr -> {
                    try {
                        return parseJson(jsonStr, TranslateResultDto.class);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to parse JSON", e);
                    }
                });
    }

    public <T> List<T> parseJson(String jsonStr, Class<T> dtoClass) throws IOException {
        List<T> list = new ArrayList<>();
        JsonNode rootNode = mapper.readTree(jsonStr);
        JsonNode choicesNode = rootNode.path("choices");

        for (JsonNode choice : choicesNode) {
            JsonNode messageNode = choice.path("message");
            String content = messageNode.path("content").asText();
            list.add(mapper.readValue(content, dtoClass));
        }

        return list;
    }

//    private List<ExplanationResponseDto> parseJson(String jsonStr) throws IOException {
//        List<ExplanationResponseDto> list = new ArrayList<>();
//        JsonNode rootNode = mapper.readTree(jsonStr);
//        JsonNode choicesNode = rootNode.path("choices");
//
//        for (JsonNode choice : choicesNode) {
//            JsonNode messageNode = choice.path("message");
//            String content = messageNode.path("content").asText();
//            list.add(mapper.readValue(content, ExplanationResponseDto.class));
//        }
//
//        return list;
//    }

}
