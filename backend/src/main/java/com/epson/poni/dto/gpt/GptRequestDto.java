package com.epson.poni.dto.gpt;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GptRequestDto {
    private String model;
    private List<Message> messages;
    private double temperature;
    private int max_tokens;
    private int top_p;

    public static GptRequestDto createDefault(String sentence) {
        List<Message> messages = Arrays.asList(
                new Message("user", "{\"sentence\": \"사슴이 이쁘다.\", \"analysis\": [{\"word\": \"사슴\", \"grammar\": \"주어\", \"word_description\": \"동물의 종류 중 하나로, 대개 크고 가늘며 뿔이 있는 동물을 일컫는다.\", \"synonyms\": [\"deer\"]}, {\"word\": \"이쁘다\", \"grammar\": \"동사\", \"word_description\": \"외모나 모습이 아름답고 매력적이라는 뜻을 나타내는 동사이다.\", \"synonyms\": [\"beautiful\", \"pretty\", \"lovely\"]}]}"),
                new Message("user", "너는 최고의 영어를 사용하는 사람들을 위한 한국어 선생님이야.내가 한국어로된 문장을 줄테니 내가준 JSON처럼 분석해서 JSON 데이터를 만들어서 줘 그리고 token 수가 4096 이내로 만들어줘"),
                new Message("user", "문장 : " + sentence)
        );
        return new GptRequestDto("gpt-3.5-turbo", messages, 0, 4096, 1);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Message {
    private String role;
    private String content;
}