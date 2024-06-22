package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Synonyms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="synonyms_id",unique = true,nullable = false)
    private Long id;

    private String synonyms;

    @ManyToOne
    @JoinColumn(name = "analysisItem_id")
    private AnalysisItem analysisItem;

    @Builder
    public void setSynonyms(String synonyms, AnalysisItem analysisItem){
        this.synonyms = synonyms;
        this.analysisItem = analysisItem;
    }

}
