package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
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

}
