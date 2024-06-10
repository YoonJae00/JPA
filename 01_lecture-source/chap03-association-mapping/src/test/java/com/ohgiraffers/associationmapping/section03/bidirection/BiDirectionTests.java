package com.ohgiraffers.associationmapping.section03.bidirection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
public class BiDirectionTests {


    @Autowired
    private BidirectionService bidirectionService;
    /* 필기.
        데이터베이스의 테이블은 외래키 하나로 양방향 조회가 가능하지만
        객체는 서로 다른 두 단방향 참조를 합쳐서 양방향 이라고 한다.
        따라서 두 개의 연관관계 중 연관관계의 주인을 정하고, 주인이 아닌
        연관관계를 추가하는 방식으로 작성한다.
        - 양방향 연관관계는 항상 설정하는 것이 아닌, 반대방향으로도 접근하여
        - 객체 그래프(참조) 할 일이 많을 때만 사용한다(설계)
        양방향 연관관계 매핑 시 연관관계의 주인은 비지니스 로직 상 중요하다 해서
        주인이 되는 것이 아니다.
        그러면 누가 주인이 될 것인가?
        외래키를 관리하는 엔티티가 주인이 되어야 한다.
        즉, 연관관계의 주인은 외래키를 가지고 있는 엔티티가 되어야 한다.
        (우리가 쓰는 테이블로 따지면 Menu 가 주인이다.)
     */

    @DisplayName("양방향 연관관계 매핑 조회 테스트1 (연관관계의 주인인 객체)")
    @Test
    public void ownerFindTest1() {

        // given
        int menuCode = 10;


        // when
        Menu foundMenu = bidirectionService.findMenu(menuCode);

        // then
        Assertions.assertEquals(menuCode, foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);
    }

    @DisplayName("양방향 연관관계 매핑 조회 테스트2 (연관관계의 주인이 아닌 객체)")
    @Test
    public void ownerFindTest2() {

        // given
        int categoryCode = 10;

        // when
        Category foundCategory = bidirectionService.findCategory(categoryCode);
        System.out.println("foundCategory = " + foundCategory);

        // then
        Assertions.assertEquals(categoryCode, foundCategory.getCategoryCode());
    }

    private static Stream<Arguments> getMenuInfo(){
        return Stream.of(
                Arguments.of(112,"스테이크",9800,"Y")
        );
    }

    @DisplayName("양방향 연관관계 주인 객체를 이용한 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getMenuInfo")
    public void ownerInsertTest1(int menuCode, String menuName, int menuPrice, String orderableStatus) {

        Category foundCategory = bidirectionService.findCategory(4);
        Menu menu = new Menu(menuCode,menuName,menuPrice,foundCategory,orderableStatus);

        Assertions.assertDoesNotThrow( () -> {
            bidirectionService.registMenu(menu);
        });
    }

    private static Stream<Arguments> getCategoryInfo(){
        return Stream.of(
                Arguments.of(111,"양방향 카테고리",null)
        );
    }

    @DisplayName("양방향 연관관계 주인이 아닌 객체를 이용한 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getCategoryInfo")
    public void ownerInsertTest2(int categoryCode, String categoryName, Integer refCategoryCode) {
        Category category = new Category(
                categoryCode,categoryName,refCategoryCode
        );
        bidirectionService.registCategory(category);

        Category foundCategory = bidirectionService.findCategory(categoryCode);
        Assertions.assertNotNull(foundCategory);
    }
}
