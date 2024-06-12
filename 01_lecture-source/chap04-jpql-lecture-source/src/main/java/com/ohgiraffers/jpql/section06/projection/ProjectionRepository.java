package com.ohgiraffers.jpql.section06.projection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectionRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Menu> singleEntityTest() {
        String jpql = "SELECT m FROM section06Menu m";
        List<Menu> menuList = entityManager.createQuery(jpql,Menu.class).getResultList();

        return menuList;
    }

    public Menu findMenu(int menuCode) {
        return entityManager.find(Menu.class, menuCode);
    }

    public BiDirectionCategory biDirectionProjection(int menuCode) {
        String jpql = "SELECT m.category FROM section06BiDirectionMenu m WHERE m.menuCode = :menuCode";
        BiDirectionCategory resultCategory = entityManager.createQuery(jpql,BiDirectionCategory.class).setParameter("menuCode", menuCode).getSingleResult();
        return resultCategory;
    }

    public List<MenuInfo>   embeddedProjection() {
        String jpql = "SELECT m.menuInfo FROM EmbeddedMenu m";
        List<MenuInfo> menuList = entityManager.createQuery(jpql,MenuInfo.class).getResultList();
        return menuList;
    }

    public List<String> scalarTypedQuery() {
        String jpql = "SELECT m.categoryName FROM section06Category m";
        List<String> cateNameList = entityManager.createQuery(jpql, String.class).getResultList();

        return cateNameList;
    }

    public List<Object[]> scalarQuery() {
        String jpql = "SELECT m.categoryName, m.categoryCode FROM section06Category m";
        List<Object[]> cateNameList = entityManager.createQuery(jpql, Object[].class).getResultList();
        return cateNameList;
    }

    public List<CategoryInfoDTO> newProjection() {
        String jpql = "SELECT new com.ohgiraffers.jpql.section06.projection.CategoryInfoDTO(c.categoryCode, c.categoryName) FROM section06Category c";
        String jpql1 = "SELECT c.categoryName, c.categoryCode FROM section06Category c";
        List<CategoryInfoDTO> newlist = entityManager.createQuery(jpql, CategoryInfoDTO.class).getResultList();
        return newlist;
    }
}
