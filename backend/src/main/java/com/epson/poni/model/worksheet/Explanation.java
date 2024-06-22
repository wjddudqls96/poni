package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Explanation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="explanation_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String sentence;

    @Column
    private String speak;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder
    public void explanationSet(String sentence, String speak, Cart cart){
        this.sentence = sentence;
        this.speak = speak;
        this.cart = cart;
    }
}
