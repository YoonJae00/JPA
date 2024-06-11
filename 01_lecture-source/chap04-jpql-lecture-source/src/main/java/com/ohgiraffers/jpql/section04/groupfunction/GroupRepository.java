package com.ohgiraffers.jpql.section04.groupfunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public long countMenuOfCategory(int categoryCode) {

        String jpql = "select count(m.menuPrice) from section04Menu m where m.categoryCode = ?1";
        long countOfMenu = entityManager.createQuery(jpql,Long.class).setParameter(1,categoryCode).getSingleResult();

        return countOfMenu;
    }

    public Long noResult(int categoryCode) {

//        String jpql = "SELECT SUM(m.menuPrice) FROM section04Menu m where m.categoryCode = :categoryCode";
//        long result = entityManager.createQuery(jpql,Long.class).setParameter("categoryCode",categoryCode).getSingleResult();

        String jpql = "SELECT SUM(m.menuPrice) FROM section04Menu m where m.categoryCode = :categoryCode";
        Long result = entityManager.createQuery(jpql,Long.class).setParameter("categoryCode",categoryCode).getSingleResult();

        return result;
    }

    public List<Object[]> selectGroupAndHaving(long minPrice) {

        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM section04Menu m GROUP BY m.categoryCode HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceCategoryList = entityManager.createQuery(jpql,Object[].class).setParameter("minPrice",minPrice).getResultList();

        return sumPriceCategoryList;
    }
}
