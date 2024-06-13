package com.ohgiraffers.nativequery.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@SpringBootTest
public class RegistTest {

    @Autowired
    private RegistService registService;

    private static Stream<Arguments> registUser() {
        return Stream.of(
                Arguments.of("user01","pass01",null,LocalDateTime.now()),
                Arguments.of("user02","pass02","ADMIN",LocalDateTime.now()),
                Arguments.of("user03","pass03","hi",LocalDateTime.now())
        );
    }

    @ParameterizedTest
    @MethodSource("registUser")
    void registerUser(String id, String pass, String role, LocalDateTime registTime) {

        Timestamp registrealtime = Timestamp.valueOf(registTime);

        registService.regist(id,pass,role,registrealtime);
    }


}
