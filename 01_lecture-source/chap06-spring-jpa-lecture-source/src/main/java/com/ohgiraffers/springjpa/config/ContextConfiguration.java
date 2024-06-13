package com.ohgiraffers.springjpa.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.ohgiraffers.springjpa")
@EnableJpaRepositories(basePackages = "com.ohgiraffers.springjpa")
@EntityScan("com.ohgiraffers.springjpa")
public class ContextConfiguration {
}
