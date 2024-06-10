package com.epson.poni.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String type;

    @Column
    private String title;

    @Column
    private String createAt;

    @Column
    private String pdfUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
