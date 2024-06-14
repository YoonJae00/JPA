package com.ohgiraffers.springjpa.menu.model.service;

import com.ohgiraffers.springjpa.menu.entity.Menu;
import com.ohgiraffers.springjpa.menu.model.dao.MenuRepository;
import com.ohgiraffers.springjpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    @Autowired
    private final MenuRepository repository;

    @Autowired
    private final ModelMapper modelMapper;


    public MenuDTO findMenuByCode(int menuCode) {
        Menu menu = repository.findById(menuCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(menu, MenuDTO.class);
    }

    public List<MenuDTO> findMenuList() {
        List<Menu> menuList = repository.findAll(Sort.by("menuCode").descending());

        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    // 오버로딩 매개변수 선언부가 다르면 이름이 같더라도 동일하게 사용가능
    public Page<MenuDTO> findMenuList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1,
                2,
                Sort.by("menuCode").descending());

//        System.out.println("pageable = " + pageable);
        Page<Menu> menuList = repository.findAll(pageable);

        return menuList.map(menu -> modelMapper.map(menu,MenuDTO.class));
    }

    // Query 메소드를 사용해서 조회하기
    public List<MenuDTO> findByMenuPrice(int menuPrice) {

//        List<Menu> menuList = repository.findByMenuPriceGreaterThan(menuPrice);
//        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuPrice(menuPrice);
//        List<Menu> menuList = repository.findByMenuPriceGreaterThanOrderByMenuName(menuPrice);
        List<Menu> menuList = repository.findByMenuPriceGreaterThan(menuPrice, Sort.by("menuPrice").descending());
        return menuList.stream()
                .map(menu -> modelMapper.map(menu, MenuDTO.class))
                .collect(Collectors.toList());
    }

    // save() 등록 관련 메소드
    @Transactional
    public void registNewMenu(MenuDTO menuDTO) {
        repository.save(modelMapper.map(menuDTO,Menu.class));
    }

    @Transactional
    public void modifyMenu(MenuDTO modifyMenu) {
        Menu foundMenu = repository.findById(modifyMenu.getMenuCode()).orElseThrow(IllegalArgumentException::new);

        // 1. setter 사용
//        foundMenu.setMenuName(modifyMenu.getMenuName());

        // 2. @Builder
//        foundMenu = foundMenu.toBuilder().menuName(modifyMenu.getMenuName()).build();
//        repository.save(foundMenu);

        // 3. Entity 클래스 내부에서 builder 패턴을 사용해서 구현
        foundMenu = foundMenu.menuName(modifyMenu.getMenuName()).builder();
        repository.save(foundMenu);
    }

    @Transactional
    public void deleteMenu(int menuCode) {
        repository.deleteById(menuCode);
    }
}
