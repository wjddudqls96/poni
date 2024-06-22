package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AnalysisItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="analysisItem_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String word;

    @Column
    private String grammar;

    @Column
    private String word_description;

    @ManyToOne
    @JoinColumn(name = "explanation_id")
    private Explanation explanation;

    @Builder
    public void setAnalysisItem(String word, String grammar, String word_description, Explanation explanation){
        this.word = word;
        this.grammar = grammar;
        this.word_description = word_description;
        this.explanation = explanation;
    }

}
