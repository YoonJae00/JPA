package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;

public class EntityLifeCycle {

    private EntityManager entityManager;

    public EntityManager getManagerInstance() {
        return this.entityManager;
    }

    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getManagerInstance();

        return entityManager.find(Menu.class,menuCode);
    }
}
