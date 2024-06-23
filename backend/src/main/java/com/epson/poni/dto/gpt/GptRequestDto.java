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
                new Message("user", "{\"sentence\": \"사슴이 이쁘다.\", \"speak\": \"[sa-su-mi i-ppeu-da]\", \"analysis\": [{\"word\": \"사슴\", \"grammar\": \"subject\", \"word_description\": \"It is a type of animal, usually referred to as a large, slender, horned animal.\", \"synonyms\": [\"deer\"]}, {\"word\": \"이쁘다\", \"grammar\": \"verb\", \"word_description\": \"It is a verb that means that the appearance or appearance is beautiful and attractive.\", \"synonyms\": [\"beautiful\", \"pretty\", \"lovely\"]}]}"),
                new Message("user", "너는 최고의 영어를 사용하는 사람들을 위한 한국어 선생님이야.내가 한국어로된 문장을 줄테니 내가준 JSON처럼 분석해서 JSON 데이터를 만들어서 줘 그리고 token 수가 4096 이내로 만들어줘"),
                new Message("user", "문장 : " + sentence)
        );
        return new GptRequestDto("gpt-3.5-turbo", messages, 0, 4096, 1);
    }

    public static GptRequestDto createBlank(String sentence, int count) {
        List<Message> messages = Arrays.asList(
                new Message("user", "내가 단어들을 주면 각 단어마다 일상적인 짧은 문장을 생성해줘. 아래와 같은 유의사항을 꼭 지켜서. 1. 각 단어마다의 문장을 생성해주면 된다. 즉 단어의 개수가 2개라면 2개의 문장을 생성해주면 되는거야. 2. 그렇게 해서 만든 문장에 내가 준 단어들이 2개 이상 포함되면 안된다. 3. 1,2,3단계를 거친 문장들을 가지고 빈칸문제로 바꿔줘. 4. 문장에 내가 준 단어들이 2개 이상 포함되면 안된다. 5. 빈칸을 처리한 부분은 \"___\"로 대치해줘. 6. 빈칸문제와 해답을 같이주는데 아래와 같은 JSON 데이터 코드로 보여줘. 7. answer 부분은 답을 매칭시켜 제공 단어를 매칭 시키지 말고"),
                new Message("user", "제공 단어들 예시 : \"축구\", \"재밌다.\", \"우리는\""),
                new Message("user", "가공된 JSON 예시 : \"{\"problems\": [{\"content_kr\": \"___는 오늘 저녁에 경기를 한다.\", \"content_en\": \"Soccer has a match tonight.\", \"answer\": \"축구\"}, {\"content_kr\": \"그 영화는 정말 ___.\", \"content_en\": \"That movie is really fun.\", \"answer\": \"재밌었다.\"}, {\"content_kr\": \"___ 내일 소풍을 간다.\", \"content_en\": \"We are going on a picnic tomorrow.\", \"answer\": \"우리는\"}]}\""),
                new Message("user", "이제 아래와 같은 제공단어를 내가 준 형식으로 만들어서 주고. 아래와 같은 제공횟수만큼 문제를 만들어줘. 앞에 ```json은 붙히지마."),
                new Message("user", "제공단어 : " + sentence),
                new Message("user", "제공횟수 : " + count)
        );

        return new GptRequestDto("gpt-4o", messages, 0, 4096, 1);
    }

    public static GptRequestDto translateSentence(String sentence) {
        List<Message> messages = Arrays.asList(
                new Message("user", "{\"sentence\": \"Hello, how are you?\",  \"translatedSentence\": \"안녕하세요, 어떻게 지내세요?\", \"pronunciation\": \"an-nyeong-ha-se-yo, eo-tteo-ke ji-nae-se-yo?\""),
                new Message("user", "너는 영어를 사용하는 사람들을 위한 한국어 선생님이야.내가 한국어로된 문장을 줄테니 내가준 JSON처럼 분석해서 JSON 데이터를 만들어서 줘 그리고 token 수가 4096 이내로 만들어줘"),
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