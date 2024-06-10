package com.ohgiraffers.associationmapping.section02.onetomany;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OneToManyService {

    @Autowired
    private OneToManyRepository oneToManyRepository;


    public Category findCategory(int categoryCode) {
        Category category = oneToManyRepository.find(categoryCode);
        System.out.println("category = " + category);
        return category;
    }

    @Transactional
    public void registMenu(CategoryDTO category) {

        List<Menu> menu = new ArrayList<>();
        Menu menu1 = new Menu(
                category.getMenuList().get(0).getMenuCode(),
                category.getMenuList().get(0).getMenuName(),
                category.getMenuList().get(0).getMenuPrice(),
                category.getMenuList().get(0).getCategoryCode(),
                category.getMenuList().get(0).getOrderableStatus()
        );
        menu.add(menu1);
        Category category1 = new Category(
                category.getCategoryCode(),category.getCategoryName(),category.getRefCategoryCode(),
                menu
        );
        System.out.println("category1 = " + category1);
        oneToManyRepository.registMenu(category1);
    }
}
