package com.ohgiraffers.nativequery.section01.simple;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "section01Category")
@Table(name = "tbl_category")
@SqlResultSetMappings( value = {
        /* 1. @Column 으로 매핑 설정이 되어 있는 경우(자동) */
        @SqlResultSetMapping(name = "categoryAutoMapping",
                entities = {@EntityResult(entityClass = Category.class)},
                columns = {@ColumnResult(name = "menu_count")}),
        /* 2. 매핑 설정을 수동으로 하는 경우 (@Column 어노테이션 생략 가능) */
        @SqlResultSetMapping(
                name = "categoryManualMapping",
                entities = {
                        @EntityResult(entityClass = Category.class, fields = {
                                @FieldResult(name = "categoryCode", column = "category_code"),
                                @FieldResult(name = "categoryName", column = "category_Name"),
                                @FieldResult(name = "refCategoryCode", column = "ref_category_code")
                        }),
                },
                columns = {@ColumnResult(name = "menu_count")}
        )
})
@NamedNativeQuery(
        name = "namedNativeQuery",
        query = "SELECT a.category_code, a.category_name, a.ref_category_code, COALESCE(v.menu_count, 0) menu_count FROM tbl_category a " +
                "LEFT JOIN (SELECT COUNT(*) AS menu_count, b.category_code FROM tbl_menu b GROUP BY b.category_code) v " +
                "ON (a.category_code = v.category_code) ORDER BY 1",
        resultSetMapping = "categoryAutoMapping"
)
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
