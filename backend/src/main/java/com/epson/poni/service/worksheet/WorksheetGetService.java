package com.epson.poni.service.worksheet;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.blank.ProblemDto;
import com.epson.poni.dto.cart.*;
import com.epson.poni.dto.dictation.Ploblem;
import com.epson.poni.dto.explanation.AnalysisItemDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.model.User.User;
import com.epson.poni.model.worksheet.*;
import com.epson.poni.repository.worksheet.*;
import com.epson.poni.service.HtmlPdfService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorksheetGetService {
    private final CartRepository cartRepository;
    private final ExplanationRepository explanationRepository;
    private final AnalysisItemRepository analysisItemRepository;
    private final BlankRepository blankRepository;
    private final SynonymsRepository synonymsRepository;
    private final TraceRepository traceRepository;
    private final HtmlPdfService htmlPdfService;


    public List<CartListAllResponse> getListAll(Authentication authentication) {
        List<CartListAllResponse> cartListAllResponseList = new ArrayList<>();

        User user = (User) authentication.getPrincipal();
        List<Cart> cartList = cartRepository.findByUser(user);

        for (Cart cart : cartList) {
            List<Explanation> explanationList = explanationRepository.findByCart(cart);
            for (Explanation explanation : explanationList) {
                String sentence = explanation.getSentence();

                CartListAllResponse cartListAllResponse = new CartListAllResponse();
                cartListAllResponse.setCartId(cart.getId());
                cartListAllResponse.setDate(cart.getDate());
                cartListAllResponse.setSentence(sentence);

                cartListAllResponseList.add(cartListAllResponse);
            }
        }

        return cartListAllResponseList;
    }

    public void getList(GetSelectCartRequestDto getSelectCartRequestDto) {
        GetSelectCartResponse cartResponse = new GetSelectCartResponse();
        cartResponse.setBlank(new ArrayList<>());
        cartResponse.setExplanation(new ArrayList<>());
        cartResponse.setTraceOption(new ArrayList<>());

        for (Long cartId : getSelectCartRequestDto.getCartIds()) {
            Optional<Cart> cart = cartRepository.findById(cartId);

            if (cart.isPresent()){
                List<ExplanationResponseDto> explanationResponseDtoList = explanationAdd(cart);
                for (ExplanationResponseDto explanationResponseDto : explanationResponseDtoList) {
                    cartResponse.getExplanation().add(explanationResponseDto);
                }

                TraceHtmlDto traceOptionDto = traceAdd(cart);
                cartResponse.getTraceOption().add(traceOptionDto);

                List<BlankResponseDto> blankResponseDtoList = blankAdd(cart);
                List<BlankHtmlDto> list = new ArrayList<>();
                for (BlankResponseDto blankResponseDto : blankResponseDtoList) {


                    for(ProblemDto problemDto :  blankResponseDto.getProblems()) {
                        String[] splitArray = problemDto.getContent_kr().split("___");
                        List<String> splitList = Arrays.asList(splitArray);
                        list.add(new BlankHtmlDto(splitList, problemDto.getContent_en(), problemDto.getAnswer()));
                    }
                }

                cartResponse.setBlank(list);
            }
        }

        System.out.println(cartResponse.getTraceOption());
        Map<String, Object> map = new HashMap<>();

        map.put("explanations", cartResponse.getExplanation());
        map.put("blanks", cartResponse.getBlank());
        map.put("traces", cartResponse.getTraceOption());

        // HTML에 넣을 변수들 지정
//        map.put("title", "Welcome to Our Website");
//        map.put("message1", "머라하노 ㅋㅋ");
//        map.put("message2", "엡손엡손엡손이다.");

        htmlPdfService.createAndUploadPdf(map);
    }

    private List<BlankResponseDto> blankAdd(Optional<Cart> cart) {
        List<Blank> blankList = blankRepository.findByCart(cart.get());
        List<BlankResponseDto> blankResponseDtoList = new ArrayList<>();
        BlankResponseDto blankResponseDto = new BlankResponseDto();
        blankResponseDto.setProblems(new ArrayList<>());

        for (Blank blank : blankList) {
            ProblemDto problemDto = new ProblemDto();
            problemDto.setAnswer(blank.getAnswer());
            problemDto.setContent_en(blank.getContent_en());
            problemDto.setContent_kr(blank.getContent_kr());

            blankResponseDto.getProblems().add(problemDto);
        }

        blankResponseDtoList.add(blankResponseDto);
        return blankResponseDtoList;
    }

    private TraceHtmlDto traceAdd(Optional<Cart> cart) {
        List<List<Character>> strs = new ArrayList<>();


        TraceHtmlDto traceOptionDto = new TraceHtmlDto();
        Trace trace = traceRepository.findByCart(cart.get());
        traceOptionDto.setCount(trace.getCount());
        traceOptionDto.setGrid(trace.getGrid());
        traceOptionDto.setBlurry(trace.getBlurry());

//        for (char c : cart.get().getContent().toCharArray()) {
//            characters.add(c);
//        }
        String inputString = cart.get().getContent();
        // 9단어씩 자르기
        for (int i = 0; i < inputString.length(); i += 9) {
            int end = Math.min(i + 9, inputString.length());
            String substring = inputString.substring(i, end);
            if (substring.length() < 9) {
                // 9글자가 되도록 공백으로 채우기
                substring = String.format("%-9s", substring);
            }
            List<Character> characters = new ArrayList<>();

            for (char c : substring.toCharArray()) {
                characters.add(c);
            }

            strs.add(characters);
        }

        traceOptionDto.setContent(strs);
        return traceOptionDto;
    }

    private List<ExplanationResponseDto> explanationAdd(Optional<Cart> cart) {
        List<Explanation> explanationList = explanationRepository.findByCart(cart.get());

        List<ExplanationResponseDto> explanationResponseDtoList = new ArrayList<>();
        for (Explanation explanation : explanationList) {
            ExplanationResponseDto explanationResponseDto = new ExplanationResponseDto();
            explanationResponseDto.setSentence(explanation.getSentence());
            explanationResponseDto.setSpeak(explanation.getSpeak());

            List<AnalysisItemDto> analysisItemDtoList = analysisItemAdd(explanation);

            explanationResponseDto.setAnalysis(analysisItemDtoList);

            explanationResponseDtoList.add(explanationResponseDto);
        }
        return explanationResponseDtoList;
    }

    private List<AnalysisItemDto> analysisItemAdd(Explanation explanation) {
        List<AnalysisItem> byExplanation = analysisItemRepository.findByExplanation(explanation);
        List<AnalysisItemDto> analysisItemDtoList = new ArrayList<>();
        for (AnalysisItem analysisItem : byExplanation) {
            AnalysisItemDto analysisItemDto = new AnalysisItemDto();
            analysisItemDto.setGrammar(analysisItem.getGrammar());
            analysisItemDto.setWord(analysisItem.getWord());
            analysisItemDto.setWord_description(analysisItem.getWord_description());

            sysnonymsAdd(analysisItem, analysisItemDto);

            analysisItemDtoList.add(analysisItemDto);
        }
        return analysisItemDtoList;
    }

    private void sysnonymsAdd(AnalysisItem analysisItem, AnalysisItemDto analysisItemDto) {
        List<Synonyms> byAnalysisItem = synonymsRepository.findByAnalysisItem(analysisItem);
        List<String> synonymsList = new ArrayList<>();
        for (Synonyms synonyms : byAnalysisItem) {
            synonymsList.add(synonyms.getSynonyms());
        }
        analysisItemDto.setSynonyms(synonymsList);
    }
}
