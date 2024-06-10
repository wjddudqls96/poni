package com.epson.poni.model;

import com.epson.poni.model.enums.AuthType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
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
    private String language;

    @Column
    private String deviceId;

    @Column
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "setting_id")
    private Setting setting;

    @Builder
    public void Change(String language, String deviceId){
        this.deviceId = deviceId;
        this.language =  language;
    }


//    @Column
//    private String userId;
//
//    @Column
//    private String password;
//
//    @Column
//    private String refreshToken;
//
//    @Column
//    @Enumerated(EnumType.STRING)
//    private AuthType type = AuthType.USER;
//
//    public User(String userId, String password){
//        this.userId = userId;
//        this.password = password;
//    }
//
//    public User() {
//
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userId='" + userId + '\'' +
//                ", password='" + password + '\'' +
//                ", type=" + type +
//                '}';
//    }
}
