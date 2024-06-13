package com.epson.poni.model.worksheet;

import com.epson.poni.model.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Blank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blank_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String content;

    @Column
    private String translation;

    @Column
    private String word;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
