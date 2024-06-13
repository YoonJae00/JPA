package com.ohgiraffers.nativequery.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistService {

    @Autowired
    private RegistRepository repository;


    @Transactional
    public void regist(String id, String pass, String role, Timestamp registTime) {
        User user = new User(id,pass,role,new Time(registTime,null));

        repository.regist(user);
    }
}
