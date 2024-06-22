package com.epson.poni.model.worksheet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trace_id",unique = true,nullable = false)
    private Long id;


    @Column
    private Boolean blurry;

    @Column
    private Boolean grid;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
