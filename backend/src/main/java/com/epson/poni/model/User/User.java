package com.epson.poni.model.User;

import com.epson.poni.model.Setting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

//@Data
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String email;

    @Column
    private String userName;

    @Column
    private String language;

    @Column
    private String deviceId;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")
    private Setting setting;

    public void Change(String language, String deviceId){
        this.deviceId = deviceId;
        this.language =  language;
    }

}
