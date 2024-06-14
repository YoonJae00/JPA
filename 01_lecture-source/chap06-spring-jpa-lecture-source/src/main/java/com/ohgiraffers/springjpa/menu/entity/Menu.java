package com.ohgiraffers.springjpa.menu.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_menu")
// 2. @Builder lombok 라이브러리에서 제공해주는 빌더 사용
//@Builder(toBuilder = true)
@AllArgsConstructor
@Getter
@ToString
public class Menu {

    @Id
    @Column(name = "menu_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name = "menu_name")
    @Setter
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    protected Menu() {}

    // 3. Entity 클래스 내부에서 builder 패턴 구현
    public Menu menuName(String var) {
        this.menuName = var;
        return this;
    }

    public Menu menuPrice(int var) {
        this.menuPrice = var;
        return this;
    }

    public Menu categoryCode(int var) {
        this.categoryCode = var;
        return this;
    }

    public Menu orderableStatus(String var) {
        this.orderableStatus = var;
        return this;
    }

    public Menu builder() {
        return new Menu(menuCode, menuName, menuPrice, categoryCode, orderableStatus);
    }
}
