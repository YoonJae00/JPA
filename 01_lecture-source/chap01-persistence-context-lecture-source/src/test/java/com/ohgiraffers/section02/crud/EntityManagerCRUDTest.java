package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class EntityManagerCRUDTest {

    private EntityManagerCRUD crud;

    @BeforeEach
    public void initManager() {
        this.crud = new EntityManagerCRUD();
    }

    @AfterEach
    public void rollback() {
        EntityTransaction transaction = crud.getManagerInstance().getTransaction();
    }

    @DisplayName("메뉴코드로 메뉴 조회 테스트")
    /* 필기.
        테스트 시에 여러 값들을 이용해서 검증을 진행해야 하는 경우에
        경우의 수 만큼 테스트 메소드를 작성해야 한다.
        @ParameterizedTest 어노테이션을 붙이게 되면 테스트 메소드에
        매개변수를 선언할 수 있다.
        (일반적인 테스트 메소드는 매개변수 사용 불가)
        파라미터로 전달할 값의 목록 만큼 반복적으로 테스트 메소드를 실행하며 준비 된 값 목록을
        전다하여 테스트를 실행할 수 있다. --> given 을 대체할 수 있다.
     */
    @ParameterizedTest
    // 필기. 여러 개의 테스트 전용 파라미터 전달. 쉼표(,) 로 값을 구분할 수 있다.
    @CsvSource(value = {"1,1","2,2","3,3","4,4"})
    public void testFindMethodByMenuCode(int menuCode, int expected) {

        // when
        Menu foundMenu = crud.findMenuByMenuCode(menuCode);

        // then
        Assertions.assertEquals(expected,foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);

    }

    private static Stream<Arguments> newMenu() {
        return Stream.of(
          Arguments.of(
                  "신메뉴", 20000, 4, "Y"
          )
        );
    }

    @DisplayName("새로운 메뉴 추가 테스트")
    @ParameterizedTest
    @MethodSource("newMenu")
    public void testRegist(String menuName, int menuPrice, int categoryCode, String orderableStatus){


        // when
        Menu newMenu = new Menu(menuName,menuPrice,categoryCode,orderableStatus);
        Long count = crud.saveAndReturnAllCount(newMenu);

        System.out.println("count = " + count);
        // then
        Assertions.assertEquals(22,count);

    }


    @DisplayName("메뉴 이름 수정 테스트")
    @ParameterizedTest
    @CsvSource("1, 변경된 메뉴")
    public void testModifyMenuName(int menuCode, String menuName){

        // when
        Menu modifyMenu = crud.modifyMenuName(menuCode,menuName);

        // then
        Assertions.assertEquals(menuName,modifyMenu.getMenuName());
    }

//    @DisplayName("연습")
//    @ParameterizedTest
//    @CsvSource(value = {"열무김치라떼","우럭스무디"})
//    public void testtest(String menuName){
//
//        // when
//        Menu selectMenu = crud.simpleSelectMenu(menuName);
//        // then
//        System.out.println("selectMenu = " + selectMenu);
//    }

    @DisplayName("메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1})
    public void testRemoveMenu(int menuCode){

        Long count = crud.removeAndReturnAllCount(menuCode);

        Assertions.assertEquals(20,count);
    }

}
