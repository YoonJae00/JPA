package com.ohgiraffers.associationmapping.section03.bidirection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "ManyToMany")
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    // 영속성 전이
    //  특정 엔티티를 영속화(등록) 할 때, 연관 된 엔티티도 함께
    //  영속화 한다는 의미이다.
    //  즉 Menu 엔티티를 영속화 할 때, Category 엔티티도 같이 영속화 시킨다.
    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;

    @Column(name = "orderable_status")
    private String orderableStatus;


}
