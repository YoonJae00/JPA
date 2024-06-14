package com.ohgiraffers.testyoonkaotalk.talk.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    private int userCode;
    private String userId;
    private String userPw;
    private String userName;
    private String userGender;
    private String status;
    private LocalDateTime startTime;

}
