package com.ohgiraffers.testyoonkaotalk.talk.entity;

import com.ohgiraffers.testyoonkaotalk.talk.embedded.Time;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_chat")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Chat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chatId;

    @Column(name = "chat_log")
    private String chatLog;

    @Embedded
    private Time time;

    @ManyToOne
    @JoinColumn(name = "user_code")
    @ToString.Exclude
    private User user;

}
