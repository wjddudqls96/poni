package com.epson.poni.model.dictation;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Difficulty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="difficulty_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String difficulty; //상,중, 하

    @Column
    private String content;


}
