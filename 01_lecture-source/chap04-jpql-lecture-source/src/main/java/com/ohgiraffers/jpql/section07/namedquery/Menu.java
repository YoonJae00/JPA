package com.ohgiraffers.jpql.section07.namedquery;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity(name = "section07Menu")
@Table(name = "tbl_menu")
@AllArgsConstructor
@Getter
@ToString
@NamedQueries({
        @NamedQuery(name = "section07Menu.selectMenuList", query = "SELECT m FROM section07Menu m")
})
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Setter
    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected Menu() {}

}
