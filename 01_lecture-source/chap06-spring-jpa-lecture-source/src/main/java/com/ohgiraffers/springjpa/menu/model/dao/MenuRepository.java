package com.ohgiraffers.springjpa.menu.model.dao;

import com.ohgiraffers.springjpa.menu.entity.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/* 필기.
    JPARepository 상속 받을 것이다. (<내가 사용할 엔티티, 해당 클래스의 아이디값>)
    Repository <- CrudRepository <- PagingAndSortingRepository <- JpaRepository
    - EntityManagerFactory, EntityManager, EntityTransaction 자동 구현
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {



    List<Menu> findByMenuPriceGreaterThanOrderByMenuPrice(int menuPrice);

    List<Menu> findByMenuPriceGreaterThanOrderByMenuName(int menuPrice);

    List<Menu> findByMenuPriceGreaterThan(int menuPrice, Sort menuPrice1);
}
