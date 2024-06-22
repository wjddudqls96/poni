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

                TraceOptionDto traceOptionDto = traceAdd(cart);
                cartResponse.getTraceOption().add(traceOptionDto);

                List<BlankResponseDto> blankResponseDtoList = blankAdd(cart);
                for (BlankResponseDto blankResponseDto : blankResponseDtoList) {
                    cartResponse.getBlank().add(blankResponseDto);
                }
            }
        }
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

    private TraceOptionDto traceAdd(Optional<Cart> cart) {
        TraceOptionDto traceOptionDto = new TraceOptionDto();
        Trace trace = traceRepository.findByCart(cart.get());
        traceOptionDto.setCount(trace.getCount());
        traceOptionDto.setGrid(trace.getGrid());
        traceOptionDto.setBlurry(trace.getBlurry());
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
