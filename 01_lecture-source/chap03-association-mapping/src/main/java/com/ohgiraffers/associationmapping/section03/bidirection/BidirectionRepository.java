package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class BidirectionRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public Menu findMenu(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public Category findCategory(int categoryCode) {
        return entityManager.find(Category.class, categoryCode);
    }

    public void registMenu(Menu menu) {
        entityManager.persist(menu);
    }

    public void registCategory(Category category) {
        entityManager.persist(category);
    }
}
