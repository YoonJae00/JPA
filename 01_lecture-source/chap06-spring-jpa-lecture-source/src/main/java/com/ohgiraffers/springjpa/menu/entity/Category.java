package com.ohgiraffers.springjpa.menu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "tbl_category")
@AllArgsConstructor
@Getter
@ToString
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    protected Category() {}
}
