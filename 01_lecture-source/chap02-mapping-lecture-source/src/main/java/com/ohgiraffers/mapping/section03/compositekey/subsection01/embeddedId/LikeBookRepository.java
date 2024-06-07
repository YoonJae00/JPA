package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LikeBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Like like) {
        entityManager.persist(like);
    }
}
