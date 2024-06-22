package com.epson.poni.service.worksheet;

import com.epson.poni.dto.blank.BlankResponseDto;
import com.epson.poni.dto.blank.ProblemDto;
import com.epson.poni.dto.cart.CombinedResultDto;
import com.epson.poni.dto.cart.TraceOptionDto;
import com.epson.poni.dto.explanation.AnalysisItemDto;
import com.epson.poni.dto.explanation.ExplanationResponseDto;
import com.epson.poni.model.User.User;
import com.epson.poni.model.worksheet.*;
import com.epson.poni.repository.worksheet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksheetSaveService {
    private final CartRepository cartRepository;
    private final AnalysisItemRepository analysisItemRepository;
    private final BlankRepository blankRepository;
    private final ExplanationRepository explanationRepository;
    private final SynonymsRepository synonymsRepository;
    private final TraceRepository traceRepository;

    public void saveCart(CombinedResultDto combinedResultDto, Authentication authentication) {

        Cart cart = saveCart(authentication);

        saveExplanations(combinedResultDto, cart);

        saveTrace(combinedResultDto, cart);

        saveBlank(combinedResultDto, cart);


    }

    private void saveBlank(CombinedResultDto combinedResultDto, Cart cart) {
        List<Blank> blanks = new ArrayList<>();
        List<BlankResponseDto> blankList = combinedResultDto.getBlank();

        for (BlankResponseDto blankResponseDto : blankList) {
            List<ProblemDto> problems = blankResponseDto.getProblems();
            for (ProblemDto problem : problems) {
                Blank blank = new Blank();
                blank.setBlank(problem.getContent_kr(),problem.getContent_en(),problem.getAnswer(), cart);
                blanks.add(blank);
            }
        }

        blankRepository.saveAll(blanks);
    }

    private void saveTrace(CombinedResultDto combinedResultDto, Cart cart) {
        Trace trace = new Trace();
        TraceOptionDto traceOption = combinedResultDto.getTraceOption();
        trace.setTrace(traceOption.isBlurry(),traceOption.isGrid(),traceOption.getCount(), cart);
        traceRepository.save(trace);
    }

    private void saveExplanations(CombinedResultDto combinedResultDto, Cart cart) {
        List<Explanation> explanations = new ArrayList<>();
        List<ExplanationResponseDto> dtoExplanation = combinedResultDto.getExplanation();

        List<AnalysisItem> analysisItems = new ArrayList<>();
        List<Synonyms> synonymsList = new ArrayList<>();

        for (ExplanationResponseDto explanationResponseDto : dtoExplanation) {
            Explanation explanation = new Explanation();
            explanation.explanationSet(explanationResponseDto.getSentence(),explanationResponseDto.getSpeak(), cart);
            explanations.add(explanation);

            //analysisItem 저장
            List<AnalysisItemDto> analysis = explanationResponseDto.getAnalysis();
            for (AnalysisItemDto item : analysis) {
                AnalysisItem analysisItem = new AnalysisItem();

                analysisItem.setAnalysisItem(item.getWord(),item.getGrammar(),item.getWord_description(),explanation);
                analysisItems.add(analysisItem);

                //synonyms 저장
                List<String> strList = item.getSynonyms();
                for (String s : strList) {
                    Synonyms synonyms = new Synonyms();
                    synonyms.setSynonyms(s,analysisItem);
                    synonymsList.add(synonyms);
                }

            }

        }

        explanationRepository.saveAll(explanations);
        analysisItemRepository.saveAll(analysisItems);
        synonymsRepository.saveAll(synonymsList);

    }

    private Cart saveCart(Authentication authentication) {
        Cart cart = new Cart();
        User user = (User) authentication.getPrincipal();
        cart.cartSet(user, new Date());
        cartRepository.save(cart);
        return cart;
    }

}
