package com.ohgiraffers.problem;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemOfUsingDirectionTests {

    /* 수업목표. 테스트 코드 기반으로 JPA 를 사용하지 않았을 때 문제를 알아보자 */

    /* 필기.
        테스트 클래스란?
        @Test 라는 annotation 이 1개 이상 가지고 있는 클래스를 의미한다.
        테스트 메소드는 반환값을 기대하지 않으며, void 형으로 작성해야 한다.
        또한 접근제한자는 사용하지 않아도 되지만(default), private 는 안된다.
     */

    private Connection con;

//    우리가 작성한 테스트 메소드가 실행하기 이전에 1번씩 동작을 할 수 있는 annotation
    @BeforeEach
    public void setConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menudb";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        System.out.println("@BeforeEach 동작");

        Class.forName(driver);

        con = DriverManager.getConnection(url,user,password);
        con.setAutoCommit(false);
    }

    @AfterEach
    public void closeConnection() throws SQLException {
        System.out.println("@AfterEach 동작");
        con.rollback();
        con.close();
    }

    /* 필기.
        JDBC 를 이용해 직접 SQL 을 다룰 때 발생할 수 있는 문제점 확인
        1. 데이터 변환, SQL 작성, JDBC 코드 중복 작성 => 개발 시간 증가 => 유지보수성 약화
        2. SQL 의존적인 개발
        3. 패러다임 불일치
        4. 동일성 보장 문제
     */

    // 1. 데이터 변환, SQL 작성, JDBC 코드 중복 작성
    @Test
    @DisplayName("직접 SQL 을 작성해서 메뉴를 조회할 때 발생하는 문제 확인")
    public void testDirectSQL() throws SQLException {

        String query = "SELECT * FROM TBL_MENU";

        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        List<Menu> menuList = new ArrayList<>();
        while (rset.next()) {
            Menu menu = new Menu();
            menu.setMenuCode(rset.getInt("MENU_CODE"));
            menu.setMenuName(rset.getString("MENU_NAME"));
            menu.setMenuPrice(rset.getInt("MENU_PRICE"));
            menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
            menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));

            menuList.add(menu);
        }

        // 필기. Assertions 클래스는 우리가 작성한 테스트 코드의 검증을 할 수 있는 기능(메소드) 를
        //      제공해주는 Class 이다.
        Assertions.assertNotNull(menuList);
        menuList.forEach(menu -> System.out.println("menu = " + menu));

        rset.close();
        stmt.close();
    }

    @Test
    @DisplayName("직접 SQL 을 작성해서 신규 메뉴 추가 시 발생하는 문제 확인")
    public void testInsertSQL() throws SQLException {

        // given
        Menu menu = new Menu();
        menu.setMenuName("커피");
        menu.setMenuPrice(3000);
        menu.setCategoryCode(1);
        menu.setOrderableStatus("Y");

        String query = "INSERT INTO TBL_MENU(MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS) VALUES(?,?,?,?)";

        // when
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, menu.getMenuName());
        pstmt.setDouble(2, menu.getMenuPrice());
        pstmt.setInt(3, menu.getCategoryCode());
        pstmt.setString(4, menu.getOrderableStatus());

        int result = pstmt.executeUpdate();

        // then
        // equals 메소드는 실제 값과 기댓값의 일치 여부를 동일성으로 판단한다.
        Assertions.assertEquals(1, result);

        pstmt.close();
    }

    // 2. SQL 에 의존적이 개발

    // 2-1. 조회 항목 요구사항 변화에 따른 의존성 문제

    /* 필기.
        고객 즉 클라이언트 측에 요구사항 변화로 인한
        DB 수정, SQL 문 수정, Application 수정 등의 문제 발생
     */

    // 2-2. 연관 된 객체에 대한 문제
    @Test
    @DisplayName("연관된 객체 문제 확인")
    public void testAssociatedObject() throws SQLException {

        // given
        String query = "SELECT a.menu_code, a.menu_name, a.menu_price, b.category_code, b.category_name, a.orderable_status " +
                "FROM tbl_menu a " +
                "JOIN tbl_category b on (a.category_code = b.category_code)";
        // when
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        List<MenuAndCategory> menuAndCategoryList = new ArrayList<>();
        while (rset.next()) {
            MenuAndCategory menuAndCategory = new MenuAndCategory();
            menuAndCategory.setMenuCode(rset.getInt("MENU_CODE"));
            menuAndCategory.setMenuName(rset.getString("MENU_NAME"));
            menuAndCategory.setMenuPrice(rset.getInt("MENU_PRICE"));
            menuAndCategory.setCategory(new Category(rset.getInt("category_code"), rset.getString("category_name")));
            menuAndCategory.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));

            menuAndCategoryList.add(menuAndCategory);
        }
        Assertions.assertNotNull(menuAndCategoryList);
        menuAndCategoryList.forEach(menu -> System.out.println("menuAndCategoryList = " + menu));

        // then
        stmt.close();
        rset.close();
    }

    // 3. 패더다임 불일치 문제(상속, 다형성, 캡슐화, 추상화)

    /* 필기.
        객체 지향 언어의 상속과 유사한 것이 데이터베이스의 서브타입 엔티티 이다.
        유사한 것 같지만 다른 부분은 데이터베이스의 상속은 상속 개념을 데이터로 추상화해서
        슈퍼타입과 서브타입으로 구분하고, 물리적으로는 다른 테이블로 분리가 된 형태이다.
     */

    // 3-1. 연관관계 문제

    /* 객체지향에서 말하는 1대1 관계 1대다 관계는 데이터베이스 저장 구조와 다른 형태이다. */

    // 4. 동일성 보장 문제
    @Test
    @DisplayName("조회한 두 개의 행을 담은 객체의 동일성 비교")
    public void testEquals() throws SQLException {

        //given
        String query = "SELECT menu_code, menu_name from tbl_menu where menu_code = 1";
        //when
        Statement stmt1 = con.createStatement();
        ResultSet rset1 = stmt1.executeQuery(query);

        Statement stmt2 = con.createStatement();
        ResultSet rset2 = stmt2.executeQuery(query);

        Menu menu1 = null;
        while (rset1.next()) {
            menu1 = new Menu();
            menu1.setMenuCode(rset1.getInt("menu_code"));
            menu1.setMenuName(rset1.getString("menu_name"));
        }

        Menu menu2 = null;
        while (rset2.next()) {
            menu2 = new Menu();
            menu2.setMenuCode(rset2.getInt("menu_code"));
            menu2.setMenuName(rset2.getString("menu_name"));
        }

        //then
        Assertions.assertFalse(menu1 == menu2);
        Assertions.assertFalse(menu1.equals(menu2));
        Assertions.assertEquals(menu1.getMenuName(),menu2.getMenuName());

        System.out.println(menu1.toString().equals(menu2.toString()));
        rset1.close();
        rset2.close();
    }

}
