package com.ohgiraffers.springjpa.menu.model.dao;

import com.ohgiraffers.springjpa.menu.entity.Category;
import com.ohgiraffers.springjpa.menu.model.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {


    @Query(value = "SELECT * from tbl_category ORDER BY category_code",
    nativeQuery = true)
    List<Category> findAllCategory();

    @Query(value = "SELECT * from tbl_category ORDER BY category_code",
            nativeQuery = true)
    List<Map<String, Object>> findMapCate();

}
