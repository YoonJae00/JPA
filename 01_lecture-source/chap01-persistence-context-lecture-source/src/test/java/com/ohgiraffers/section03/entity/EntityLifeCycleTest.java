package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EntityLifeCycleTest {

    private EntityLifeCycle entityLifeCycle;

    @BeforeEach
    public void setUp() {
        this.entityLifeCycle = new EntityLifeCycle();
    }

    @DisplayName("비영속 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void testTransient(int menuCode) {

        Menu findmenu = entityLifeCycle.findMenuByMenuCode(menuCode);

        Menu newMenu = new Menu(
                findmenu.getMenuCode(),findmenu.getMenuName(),findmenu.getMenuPrice(),findmenu.getCategoryCode(),findmenu.getOrderableStatus()
        );

        Assertions.assertNotEquals(findmenu,newMenu);
        Assertions.assertFalse(findmenu == newMenu);
        System.out.println(findmenu.getMenuName().equals(newMenu.getMenuName()));
        Assertions.assertTrue(entityLifeCycle.getManagerInstance().contains(findmenu));
        Assertions.assertFalse(entityLifeCycle.getManagerInstance().contains(newMenu));

    }

    @DisplayName("다른 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void testOtherManager(int menuCode) {
        Menu findmenu1 = entityLifeCycle.findMenuByMenuCode(menuCode);
        Menu findmenu2 = entityLifeCycle.findMenuByMenuCode(menuCode);

        System.out.println("findmenu1 = " + findmenu1.hashCode());
        System.out.println("findmenu2 = " + findmenu2.hashCode());
        Assertions.assertNotEquals(findmenu1,findmenu2);
    }

    @DisplayName("같은 엔티티 매니저가 관리하는 엔티티의 영속성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void testSameManager(int menuCode) {
        EntityManager manager = EntityManagerGenerator.getManagerInstance();

        Menu menu1 = manager.find(Menu.class, menuCode);
        Menu menu2 = manager.find(Menu.class, menuCode);

        Assertions.assertEquals(menu1,menu2);
    }
}
