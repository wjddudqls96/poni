package com.epson.poni.model.dictation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    private Difficulty difficulty;

}
