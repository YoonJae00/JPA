package com.ohgiraffers.section03.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.security.AllPermission;
import java.util.List;

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

    @DisplayName("준영속화 detach xptmxm")
    @ParameterizedTest
    @CsvSource({"11,1000","12,1000"})
    public void testDetachEntity(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();

        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();

        /* 필기. detach()
            특정 엔티티만 준영속 상태(영속성 컨텍스트가 관리하던 엔티티 객체를 관리하지 않는 상태) 로 만든다.
         */

        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        manager.flush();

//        Assertions.assertNotEquals(menuPrice,foundMenu.getMenuPrice());
        Assertions.assertEquals(menuPrice,foundMenu.getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("준영속화 detach 후 다시 영속화 테스트")
    @ParameterizedTest(name = "[{index}] 준 영속화 된 {0} 번 메뉴를 {1}원으로 변경하고 다시 영속화 되는 지 확인")
    @CsvSource({"11,1000","12,1000"})
    public void testDetachAndpersist(int menuCode, int menuPrice) {

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);
        transaction.begin();
        manager.detach(foundMenu);
        foundMenu.setMenuPrice(menuPrice);

        /* 필기.
            파라미터로 넘어온 준영속 엔티티 객체의 식별자 값으로 1차 캐시에서 엔티티 객체를 조회한다.
            만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고 1차 캐시에 저장한다.
            조회한 영속 엔티티 객체에 준영속 상태의 엔티티 객체의 값을 병합한 뒤 영속 엔티티 객체를 반환한다.
            혹은 조회할 수 없는 데이터의 경우 새로 생성해서 병합한다. (save or update)
         */
        manager.merge(foundMenu);
        manager.flush();

        Assertions.assertEquals(menuPrice,manager.find(Menu.class, menuCode).getMenuPrice());
        transaction.rollback();
    }

    @DisplayName("detach 준영속 후 merge 한 데이터 save 테스트")
    @Test
    public void testDetachAndMergeSave() {
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Menu foundMenu = manager.find(Menu.class, 20);
        manager.detach(foundMenu);
        foundMenu.setMenuCode(22);
        foundMenu.setMenuName("닭가슴살샐러드");
        manager.merge(foundMenu);
        manager.flush();
        Assertions.assertEquals(22,manager.find(Menu.class, 20).getMenuCode());
        transaction.rollback();
    }

    @DisplayName("준영속화 clear 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void testClearPersistenceContext(int menuCode){

        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        // clear() : 영속성 컨텍스트를 초기화 -> 영속성 컨텍스트 내의 모든 엔티티를 준영속화 시킨다.
        manager.clear();

        Menu expectedMenu = manager.find(Menu.class,menuCode);
        Assertions.assertNotEquals(expectedMenu,foundMenu);
    }

    @DisplayName("준영속화 close 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void testClosePersistenceContext(int menuCode){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        // close() : 영속성 컨텍스트를 종료한다. -> 영속성 컨텍스트 내 모든 객체를 준영속화한다.
        manager.close();

//        Menu expectedMenu = manager.find(Menu.class,menuCode);
//        Assertions.assertNotEquals(expectedMenu,foundMenu);
        Assertions.assertThrows(IllegalStateException.class, () -> manager.find(Menu.class, menuCode));
    }

    @DisplayName("영속성 엔티티 삭제 remove 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    public void testRemoveEntity(int menuCode){
        EntityManager manager = EntityManagerGenerator.getManagerInstance();
        EntityTransaction transaction = manager.getTransaction();
        Menu foundMenu = manager.find(Menu.class, menuCode);

        transaction.begin();
        /* 필기.
            remove()
            엔티티를 영속성 컨텍스트 및 데이터베이스에서 삭제한다.
            단, 트렌젝션을 제어하지 않으면 데이터베이스에 영구 반영되지는 않는다.
            트랜젝션을 커밋 or 플러ㅜ시 하는 순간 영속성 컨텍스트에서
            관리하는 엔티티 객체가 데이터 베이스에 반영된다.
            엔티티 자체를 삭제해버림
         */
        manager.remove(foundMenu);

        manager.flush();

        Assertions.assertFalse(manager.contains(foundMenu));
        Menu refoundMenu = manager.find(Menu.class, menuCode);
        Assertions.assertNull(refoundMenu);

        transaction.rollback();
    }
}

