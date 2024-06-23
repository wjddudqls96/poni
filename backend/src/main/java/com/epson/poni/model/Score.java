package com.epson.poni.model;

import com.epson.poni.model.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="score_id",unique = true,nullable = false)
    private Long id;


    private Integer problemsCount;

    private Integer correct;

    private Integer incorrect;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public void setScore(Integer problemsCount, Integer correct, Integer incorrect, User user){
        this.problemsCount = problemsCount;
        this.correct = correct;
        this.incorrect = incorrect;
        this.user = user;
    }
}
