package com.ohgiraffers.springjpa.menu.controller;

import com.ohgiraffers.springjpa.common.Pagenation;
import com.ohgiraffers.springjpa.common.PagingButton;
import com.ohgiraffers.springjpa.menu.entity.Menu;
import com.ohgiraffers.springjpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springjpa.menu.model.dto.MenuDTO;
import com.ohgiraffers.springjpa.menu.model.service.CategoryService;
import com.ohgiraffers.springjpa.menu.model.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{menuCode}")
    public String finMenuByCode(@PathVariable("menuCode") int menuCode, Model model) {

        MenuDTO menu = menuService.findMenuByCode(menuCode);
        System.out.println("menu = " + menu);
        model.addAttribute("menu", menu);
        return "menu/detail";
    }

    @GetMapping("/list")
    public String finMenuList(Model model, @PageableDefault Pageable pageable) {
        // no paging 버전
//        List<MenuDTO> menuList = menuService.findMenuList();
//
//        model.addAttribute("menu", menuList);

        // paging 버전

        log.info("pageable : {}", pageable);

        Page<MenuDTO> menuList = menuService.findMenuList(pageable);
        log.info("조회한 내용 목록 : {}", menuList.getContent());
        log.info("총 페이지 수 : {}", menuList.getTotalPages());
        log.info("총 메뉴의 수 : {}", menuList.getTotalElements());
        log.info("해당 페이지에 표시 될 요소의 수 : {}", menuList.getSize());
        log.info("해당 페이지에 실제 요소 갯수 : {}", menuList.getNumberOfElements());
        log.info("첫 페이지 여부 : {}", menuList.isFirst());
        log.info("마지막 페이지 여부 : {}", menuList.isLast());
        log.info("정렬 방식 : {}", menuList.getSort());
        log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

        PagingButton paging = Pagenation.getPagingButtonInfo(menuList);
        model.addAttribute("menu", menuList);
        model.addAttribute("paging", paging);
        return "menu/detail";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage(){

    }

    @GetMapping("/search")
    public String findByMenuPrice(Model model, @RequestParam int menuPrice){

        List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);
        model.addAttribute("menuList", menuList);
        model.addAttribute("menuPrice", menuPrice);

        return "menu/searchResult";
    }


    @GetMapping("/regist")
    public void registPage() {

    }

    @GetMapping(value = "/category", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<CategoryDTO> addCategory() {

        List<CategoryDTO> getcate = categoryService.getCategory();

        return getcate;
    }

    @PostMapping("/regist")
    public String registNewMenu(MenuDTO menuDTO) {

        menuService.registNewMenu(menuDTO);

        return "redirect:/menu/list";
    }
}
