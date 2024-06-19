package com.epson.poni.model;

import com.epson.poni.model.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="setting_id",unique = true,nullable = false)
    private Long id;

    @Column
    private Integer columnCount;

    @Column
    private Boolean trace;

    @Column
    private Boolean guide;

    @Column
    private Integer problems;

    @OneToOne(mappedBy = "setting")
    private User user;
}
