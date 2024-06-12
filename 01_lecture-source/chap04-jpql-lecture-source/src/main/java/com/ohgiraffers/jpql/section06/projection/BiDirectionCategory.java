package com.ohgiraffers.jpql.section06.projection;

import com.ohgiraffers.jpql.section05.join.Menu;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity(name = "section06BiDirectionCategory")
@Table(name = "tbl_category")
@AllArgsConstructor
@Getter
@ToString
public class BiDirectionCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private List<BiDirectionMenu> menuList;

    protected BiDirectionCategory() {}
}
