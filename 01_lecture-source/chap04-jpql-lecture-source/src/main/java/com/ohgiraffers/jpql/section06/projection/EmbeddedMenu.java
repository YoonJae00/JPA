package com.ohgiraffers.jpql.section06.projection;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "EmbeddedMenu")
@Table(name = "tbl_menu")
@AllArgsConstructor
@Getter
@ToString
public class EmbeddedMenu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Embedded
    private MenuInfo menuInfo;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected EmbeddedMenu() {}
}
