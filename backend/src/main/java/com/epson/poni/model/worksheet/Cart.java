package com.epson.poni.model.worksheet;

import com.epson.poni.model.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id",unique = true,nullable = false)
    private Long id;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public void cartSet(User user, Date date){
        this.user = user;
        this.date = date;
    }
}
