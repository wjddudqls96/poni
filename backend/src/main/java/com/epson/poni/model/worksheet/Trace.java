package com.epson.poni.model.worksheet;

import com.epson.poni.model.Cart;
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
    private String content;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
