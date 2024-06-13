package com.ohgiraffers.nativequery.test;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "tbl_user")
@Getter
@ToString
public class User {

    @Id
    @Column(name = "user_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userCode;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pass")
    private String userPass;

    @Column(name = "user_status")
    private String userStatus = "USER";

    @Embedded
    private Time time;

    protected User() {}

    public User(String userName, String userPass, String userStatus, Time time) {
        this.userName = userName;
        this.userPass = userPass;
        this.userStatus = userStatus;
        this.time = time;
    }
}
