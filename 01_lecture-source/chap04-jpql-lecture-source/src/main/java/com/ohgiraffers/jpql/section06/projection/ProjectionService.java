package com.ohgiraffers.jpql.section06.projection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectionService {

    @Autowired
    private ProjectionRepository repository;


//    @Transactional
    public List<Menu> singleEntityTest() {
        List<Menu> menuList = repository.singleEntityTest();

        Menu menu = repository.findMenu(1);
        System.out.println("menu = " + menu);

        menuList.get(7).setMenuName("새로운 이름");

        return menuList;
    }

    @Transactional
    public BiDirectionCategory biDirectionProjection(int menuCode) {
        BiDirectionCategory categoryOfMenu = repository.biDirectionProjection(menuCode);
        categoryOfMenu.getMenuList().forEach(System.out::println);
        return categoryOfMenu;
    }
}
