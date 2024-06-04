package com.ohgiraffers.section01.entityManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryGenerator {

    private static EntityManagerFactory entityManagerFactory
            = Persistence.createEntityManagerFactory("jpatest");

    private EntityManagerFactoryGenerator() {
    }

    public static EntityManagerFactory getInstance(){
        return entityManagerFactory;
    }
}
