package com.ohgiraffers.associationmapping.section02.onetomany;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity(name = "section02Category")
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

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_code")
    private List<Menu> menuList;

    protected Category() {}
}
