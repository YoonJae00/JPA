package com.ohgiraffers.mapping.section02.embedded;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public void registBook(Book book) {
        entityManager.persist(book);
    }
}
