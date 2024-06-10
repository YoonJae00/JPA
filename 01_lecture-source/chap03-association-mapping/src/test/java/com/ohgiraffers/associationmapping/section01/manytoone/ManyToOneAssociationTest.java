package com.ohgiraffers.associationmapping.section01.manytoone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class ManyToOneAssociationTest {


    @Autowired
    private ManyToOneService manyToOneService;

    // 수업목표. 연관관계 Mapping 에 대해 이해해보자

    /* 필기.
        Entity 클래스 간의 관계를 매핑하는 것을 의미한다.
        -> 이를 통해서 객체를 이용하여 데이터베이스의 테이블 간 관계를 매핑할 수 있다.
        [다중성에 의한 분류]
        : 연관관계가 있는 객체 관계에서 실제로 연관을 가지는 객체의 수에 따라서 뷴류된다.
        - 1:1 (OneToOne)
        - 1:N (OneToMany)
        - N:1 (ManyToOne)
        - N:N (ManyToMany)
        [방향에 따른 분류]
        : 테이블의 연관 관계는 외래키를 이용한 양방향 연관 관계의 특징을 가진다.
        하지만 참조에 의한 객체의 연관 관계는 단방향이다.
        객체 간의 연관 관계를 양방향으로 만들고 싶은 경우 반대 쪽에서도
        필드를 추가해서 참조를 보관하면 된다.
        하지만 엄밀하게 말해서는 양방향이 아닌 단방향 관계가 2개로 볼 수 있다.


      필기.
        ManyToOne 은 다수의 엔티티가 하나의 엔티티를 참조하는 상황에서 사용된다.
        예를 들어 하나의 카테고리가 여러 개의 메뉴를 가질 수 있는 상황에서
        메뉴 엔티티가 카테고리 엔티티를 참조하고 있는 것이다.
        이 때, Menu 엔티티는 Many, Category 엔티티는 One 이 된다.
        연관 관계를 가지는 엔티티를 조회하는 방법은
        1. 객체 그래프 탐색(객체 연관 관계를 사용한 조회) (참조 연산자 . 을 통해 접근 할수있음)
        2. 객체 지향 쿼리(JPQL)
     */

    @Test
    public void menu() {
        Assertions.assertDoesNotThrow( () -> {
            Menu allMenu = manyToOneService.searchMenuAll();
            System.out.println("allMenu = " + allMenu);
        });
    }

    @DisplayName("N:1 연관관계 객체 그래피 탐색을 이용한 조회 테스트")
    @Test
    public void manyToOneTest() {

        // given
        int menuCode = 10;

        // when
        Menu foundMenu = manyToOneService.findMenu(menuCode);

        // then
        Category category = foundMenu.getCategory();
        System.out.println("category = " + category);
        Assertions.assertNotNull(category);
    }



    @DisplayName("N:1 연관관계 객체지향쿼리(JPQL) 사용 카테고리 이름 조회 테스트")
    @Test
    public void manyToOneJPQLTest() {
        int menuCode = 10;

        String categoryName = manyToOneService.findCategoryNameByJpql(menuCode);

        System.out.println("categoryName = " + categoryName);
        Assertions.assertNotNull(categoryName);
    }

    private static Stream<Arguments> getInfo() {
        return Stream.of(
                Arguments.of(123,"돈까스 스파게티",30000,123,"퓨전분식","Y")
        );
    }

    /* 필기.
        commit() 을 할 경우에는 컨텍스트 내에 저장 된
        영속성 객체를 insert 하는 쿼리문이 동작을 한다.
        단, 카테고리가 존재하는 값이 아니기 때문에
        부모 테이블(tbl_category) 에 먼저 값이 들어 있어야
        그 카테고리 코드를 참조하는 자식 테이블(tbl_menu) 에
        데이터를 넣을 수 있게 된다.
        이떄 필요한 것이
        @ManyToOne 어노테이션에 영속성 전이 설정을 해주는 것이다.
        영속성 전이란 특정 엔티티를 영속화 할 때
        연관 된 엔티티도 함께 영속화를 진행한다는 의미이다.
        즉, cascade = CascadeType.PERSIST 를 설정하게 된다면
        menu_and_category 엔티티 영속화 할 때 category 엔티티도
        함께 영속화를 진행해준다.
     */
    @DisplayName("N:1 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getInfo")
    public void manyToOneTest(int menuCode, String menuName, int menuPrice, int categoryCode, String categoryName, String orderableStatus) {

        MenuDTO menu = new MenuDTO(menuCode,
                menuName,
                menuPrice,
                new CategoryDTO(categoryCode,categoryName,null),
                orderableStatus);

        Assertions.assertDoesNotThrow( () -> {
            manyToOneService.registMenu(menu);
        });

    }
}
