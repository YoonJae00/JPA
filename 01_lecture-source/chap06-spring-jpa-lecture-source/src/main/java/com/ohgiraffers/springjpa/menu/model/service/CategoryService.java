package com.ohgiraffers.springjpa.menu.model.service;

import com.ohgiraffers.springjpa.menu.entity.Category;
import com.ohgiraffers.springjpa.menu.model.dao.CategoryRepository;
import com.ohgiraffers.springjpa.menu.model.dto.CategoryDTO;
import com.ohgiraffers.springjpa.menu.model.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<CategoryDTO> getCategory() {

        List<Category> cateList = categoryRepository.findAllCategory();
        List<Map<String,Object>> cateList2 = categoryRepository.findMapCate();
        for (Map<String, Object> map : cateList2) {
            System.out.println("Map:");
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
        }
        return cateList.stream()
                .map(cate -> modelMapper.map(cate, CategoryDTO.class))
                .collect(Collectors.toList());
//        return cateList;
    }
}
