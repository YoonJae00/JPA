package com.ohgiraffers.associationmapping.section02.onetomany;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class OneToManyTest {

    @Autowired
    private OneToManyService oneToManyService;


    @DisplayName("1:N 연관 관계 객체 그래프 탐색을 이용한 조회 테스트")
    @Test
    public void oneToManyFindTest() {

        int categoryCode = 10;

        Category category = oneToManyService.findCategory(categoryCode);

        Assertions.assertNotNull(category);

    }


    private static Stream<Arguments> getInfo() {
        return Stream.of(
                Arguments.of(123,"돈까스 스파게티",30000,123,"퓨전분식","Y")
        );
    }


    /* 필기
        @OneToMany 일 때 조회 임에도 불구하고 @Transactional 어노테이션을 사용하는 이유
        - 1개의 엔티티가 여러 개의 엔티티를 로드해야 하는 상황 (Category -> MenuList )
        - 일 때는 여러 개의 엔티티를 로드해야 하기 때문에 성능상의 이슈가 발생할 수 있다.
        - 그래서 Category 엔티티를 조회할 때 Menu 에 대한 엔티티를 바로 로드하는 것이 아닌
        - 일단 가지고 있다가 진짜 필요할 때 로드를 하게 된다. (Lazy)
        - Default
        - @OneToMany ( fetch = FetchType.LAZY )
        - @ManyToMany           - 이 두가지는 지연로딩이 default 이다.
        - @ManyToOne(fetch = FetchType.EAGER)
        - @OneToOne
                                - 이 두 가지는 이른로딩이 default 이다.
     */
    @DisplayName("1:N 연관관계 객체 삽입 테스트")
    @ParameterizedTest
    @MethodSource("getInfo")
    public void oneToManyInsertTest(int menuCode, String menuName, int menuPrice, int categoryCode, String categoryName, String orderableStatus) {

        List<MenuDTO> menuList = new ArrayList<>();
        MenuDTO menuDTO = new MenuDTO(
                menuCode,menuName,menuPrice,categoryCode,orderableStatus
        );
        menuList.add(menuDTO);
        CategoryDTO category = new CategoryDTO(
                categoryCode,categoryName,null,menuList
        );

        Assertions.assertDoesNotThrow( () -> {
            oneToManyService.registMenu(category);
        });
    }
}
