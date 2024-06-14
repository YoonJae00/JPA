package com.ohgiraffers.testyoonkaotalk.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.ohgiraffers.testyoonkaotalk")
@EnableJpaRepositories(basePackages = "com.ohgiraffers.testyoonkaotalk")
@EntityScan("com.ohgiraffers.testyoonkaotalk")
public class ContextConfig {


}
