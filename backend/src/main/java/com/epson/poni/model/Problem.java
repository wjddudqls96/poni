package com.epson.poni.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="problem_id",unique = true,nullable = false)
    private Long id;

    private Long dictation_id;
    private String answer;
    private String input;

    @Builder
    public void setProblem(Long dictation_id, String answer, String input){
        this.dictation_id = dictation_id;
        this.answer = answer;
        this.input = input;
    }

}
