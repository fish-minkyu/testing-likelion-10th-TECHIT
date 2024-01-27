package com.example.contents.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "user table") // DB에 실제 어느 이름, 옵션, 제약사항들을 넣으려고 설정하는 어노테이션
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Long id;

    @Column(unique = true)
    private String username;
    private String email;
    @Setter
    private String phone;
    @Setter
    private String bio;
    @Setter
    private String avatar;

    public User(String username, String email, String phone, String bio) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.bio = bio;
    }
}
