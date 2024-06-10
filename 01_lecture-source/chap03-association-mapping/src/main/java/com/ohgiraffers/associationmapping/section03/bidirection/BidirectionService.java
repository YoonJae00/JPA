package com.ohgiraffers.associationmapping.section03.bidirection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BidirectionService {

    @Autowired
    private BidirectionRepository bidirectionRepository;

    public Menu findMenu(int menuCode) {
       return bidirectionRepository.findMenu(menuCode);
    }

    // @OneToMany 이기 떄문에
    @Transactional
    public Category findCategory(int categoryCode) {
        Category category =  bidirectionRepository.findCategory(categoryCode);
//        System.out.println("category = " + category.getMenuList());
        return category;
    }

    @Transactional
    public void registMenu(Menu menu) {
        bidirectionRepository.registMenu(menu);
    }

    @Transactional
    public void registCategory(Category category) {
        bidirectionRepository.registCategory(category);
    }
}
