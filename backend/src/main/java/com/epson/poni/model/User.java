package com.epson.poni.model;

import com.epson.poni.model.enums.AuthType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@Data
@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",unique = true,nullable = false)
    private Long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String language;

    @Column
    private String deviceId;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")
    private Setting setting;


    @Column
    @Enumerated(EnumType.STRING)
    private AuthType type = AuthType.USER;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }

    @Builder
    public void Change(String language, String deviceId){
        this.deviceId = deviceId;
        this.language =  language;
    }
}
