package com.ohgiraffers.jpql.section07.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NamedQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Menu> selectByDynamicQuery(String searchName, int searchCode) {
        StringBuilder jpql = new StringBuilder(
                "SELECT m FROM section07Menu m"
        );

        if (searchName != null && !searchName.isEmpty() && searchCode != 0) {
            jpql.append(" WHERE m.menuName Like '%' || :menuName || '%' ");
            jpql.append(" AND m.categoryCode = :categoryCode ");
        } else {
            if (searchName != null && !searchName.isEmpty()) {
                jpql.append(" WHERE m.menuName LIKE '%' || :menuName || '%' ");
            } else if (searchCode > 0) {
                jpql.append(" WHERE m.categoryCode = :categoryCode ");
            }
        }

//        List<Menu> menuList = entityManager.createQuery(jpql.toString(), Menu.class).setParameter("menuName",searchName).setParameter("categoryCode",searchCode).getResultList();
        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);
        if(searchName != null && !searchName.isEmpty() && searchCode > 0) {
            query.setParameter("menuName", searchName);
            query.setParameter("categoryCode", searchCode);
        } else {
            if (searchName != null && !searchName.isEmpty()) {
                query.setParameter("menuName", searchName);
            } else if (searchCode > 0) {
                query.setParameter("categoryCode", searchCode);
            }
        }
        List<Menu> menuList = query.getResultList();
        return menuList;
    }

    public List<Menu> selectByNamedQuery() {

        List<Menu> menuList = entityManager.createNamedQuery("section07Menu.selectMenuList", Menu.class).getResultList();

        return menuList;
    }
}
