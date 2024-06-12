package com.ohgiraffers.jpql.section06.projection;

import com.ohgiraffers.jpql.section05.join.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "section06BiDirectionMenu")
@Table(name = "tbl_menu")
@AllArgsConstructor
@Getter
@ToString
public class BiDirectionMenu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @ManyToOne
    @JoinColumn(name = "category_code")
    private BiDirectionCategory category;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected BiDirectionMenu() {}
}
