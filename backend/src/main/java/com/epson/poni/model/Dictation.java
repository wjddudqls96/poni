package com.epson.poni.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Dictation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dictation_id",unique = true,nullable = false)
    private Long id;

    private Long userId;
    private Long difficultyId;

    private String content;

    @Builder
    public void setDictation(Long user_id, String content,Long difficulty_id){
        this.userId = user_id;
        this.content = content;
        this.difficultyId = difficulty_id;
    }
}
