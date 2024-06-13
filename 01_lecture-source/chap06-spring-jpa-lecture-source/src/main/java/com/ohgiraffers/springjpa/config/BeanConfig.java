package com.ohgiraffers.springjpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {

        /* 필기.
            entity 클래스에서 setter 지양 -> 그러면 값을 어떻게 넣을까
            private 필드에 접근할 수 있도록 설정 필요
            entity 와 dto 간의 변환을 용이하게 만들도록 도와주는
            라이브러리
         */
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        return modelMapper;
    }
}
