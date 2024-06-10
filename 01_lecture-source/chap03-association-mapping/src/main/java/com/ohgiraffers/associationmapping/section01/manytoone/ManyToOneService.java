package com.ohgiraffers.associationmapping.section01.manytoone;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManyToOneService {

    @Autowired
    private ManyToOneRepository manyToOneRepository;


    public Menu searchMenuAll() {
        return manyToOneRepository.searchMenuAll();
    }

    public Menu findMenu(int menuCode) {
        return manyToOneRepository.findMenu(menuCode);
    }

    @Transactional
    public String findCategoryNameByJpql(int menuCode) {
        return manyToOneRepository.findCategoryNameByJpql(menuCode);
    }

    @Transactional
    public void registMenu(MenuDTO menu) {
        Menu newMenu = new Menu(
                menu.getMenuCode(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                new Category(menu.getCategory().getCategoryCode(), menu.getCategory().getCategoryName(), null),
                menu.getOrderableStatus()
        );

        manyToOneRepository.registMenu(newMenu);

    }
}
