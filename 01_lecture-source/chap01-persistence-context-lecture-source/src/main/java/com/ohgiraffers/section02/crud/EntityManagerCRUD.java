package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class EntityManagerCRUD {

    private EntityManager entityManager;

    public EntityManager getManagerInstance() {
        return entityManager;
    }

    public Menu findMenuByMenuCode(int menuCode) {
        entityManager = EntityManagerGenerator.getManagerInstance();

        return entityManager.find(Menu.class, menuCode);
    }

    public Long saveAndReturnAllCount(Menu newMenu) {

        entityManager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(newMenu);
        entityManager.flush();
        return getCount(entityManager);
    }

    private Long getCount(EntityManager entityManager) {
        return entityManager.createQuery("select count(*) from section02Menu", Long.class).getSingleResult();
    }

    public Menu modifyMenuName(int menuCode, String menuName) {

        Menu findMenu = findMenuByMenuCode(menuCode);
        System.out.println("findMenu = " + findMenu);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("transaction = " + transaction);
        System.out.println("findMenubegin = " + findMenu);
        findMenu.setMenuName(menuName);
        entityManager.flush();
        System.out.println("findMenu2 = " + findMenu);
        return findMenu;
    }
}
