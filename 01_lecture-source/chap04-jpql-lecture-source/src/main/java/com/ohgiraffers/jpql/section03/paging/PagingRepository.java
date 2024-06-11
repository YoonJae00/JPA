package com.ohgiraffers.jpql.section03.paging;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PagingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> usingPagingAPI(int offset, int limit) {
        String jpql = "SELECT m FROM section03Menu m ORDER BY m.menuCode";
        List<Menu> pagingMenuList = entityManager.createQuery(jpql,Menu.class).setFirstResult(offset).setMaxResults(limit).getResultList();
        return pagingMenuList;
    }
}
