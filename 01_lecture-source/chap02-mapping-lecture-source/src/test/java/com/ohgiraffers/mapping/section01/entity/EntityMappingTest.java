package com.ohgiraffers.mapping.section01.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
public class EntityMappingTest {

    @Autowired
    private MemberRegistService memberRegistService;

    private static Stream<Arguments> getMember() {
        return Stream.of(
                Arguments.of(1,"user01","pass01","김윤재","010-1234-1234","서울시 노원구", LocalDateTime.now(), "ROLE_MEMBER","Y"),
                Arguments.of(2,"user02","pass02","김재윤","010-4321-4321","서울시 마포구", LocalDateTime.now(), "ROLE_ADMIN","Y")
        );
    }

    @DisplayName("테이블 생성 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testCreateTable(int memberNo, String memberId, String memberPw, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {

        // given
        MemberRegistDTO memberRegistDTO = new MemberRegistDTO(
                memberId,memberPw,memberName,phone,address,enrollDate,memberRole,status);

        Assertions.assertDoesNotThrow( () -> memberRegistService.registMember(memberRegistDTO));
    }

    @DisplayName("프로퍼티 접근 테스트")
    @ParameterizedTest
    @MethodSource("getMember")
    void testAccessProperty(int memberNo, String memberId, String memberPw, String memberName, String phone, String address, LocalDateTime enrollDate, MemberRole memberRole, String status) {

        // given
        MemberRegistDTO memberRegist = new MemberRegistDTO(
                memberId,memberPw,memberName,phone,address,enrollDate,memberRole,status);

        // when
        String registName = memberRegistService.registMemberAndFindName(memberRegist);

        Assertions.assertEquals(memberName,registName);
    }
}