package com.epson.poni.model.worksheet;

import com.epson.poni.model.Cart;
import jakarta.persistence.*;
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
    private String word;

    @Column
    private String grammar;

    @Column
    private String word_description;

    @Column
    private String synonyms;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
