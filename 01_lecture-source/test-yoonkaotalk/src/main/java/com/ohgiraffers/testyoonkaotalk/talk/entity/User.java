package com.ohgiraffers.testyoonkaotalk.talk.entity;

import com.ohgiraffers.testyoonkaotalk.talk.embedded.Status;
import com.ohgiraffers.testyoonkaotalk.talk.embedded.Time;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCode;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String userPw;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_gender")
    private String userGender;

    @OneToMany(mappedBy = "user")
    private List<Chat> chat;

    @Embedded
    private Status status;

    @Embedded
    private Time time;


    public User(String userId, String userPw, String userName, String userGender, List<Chat> chat, Status status, Time time) {
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userGender = userGender;
        this.chat = chat;
        this.status = status;
        this.time = time;
    }
}
