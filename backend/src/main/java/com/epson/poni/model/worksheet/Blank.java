package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
import lombok.Builder;
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
    private String content_kr;

    @Column
    private String content_en;

    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder
    public void setBlank(String content_kr, String content_en, String answer, Cart cart){
        this.content_kr = content_kr;
        this.content_en = content_en;
        this.answer = answer;
        this.cart = cart;
    }
}
