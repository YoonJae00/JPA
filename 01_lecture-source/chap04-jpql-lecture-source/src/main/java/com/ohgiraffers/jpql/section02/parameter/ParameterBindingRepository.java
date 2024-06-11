package com.ohgiraffers.jpql.section02.parameter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParameterBindingRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public Menu selectMenuByBindingName(String menuName) {
        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = :menuName";
        Menu menu = entityManager.createQuery(jpql,Menu.class).setParameter("menuName", menuName).getSingleResult();
        return menu;
    }

    public Menu selectMenuByBindingPosition(String menuName) {
        String jpql = "SELECT m FROM section02Menu m WHERE m.menuName = ?1";
        Menu menu = entityManager.createQuery(jpql,Menu.class).setParameter(1,menuName).getSingleResult();

        return menu;

    }
}
