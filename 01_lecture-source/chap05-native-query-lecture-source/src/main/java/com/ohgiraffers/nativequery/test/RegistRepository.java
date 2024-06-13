package com.ohgiraffers.nativequery.test;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RegistRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void regist(User user) {
        entityManager.persist(user);
    }

}
