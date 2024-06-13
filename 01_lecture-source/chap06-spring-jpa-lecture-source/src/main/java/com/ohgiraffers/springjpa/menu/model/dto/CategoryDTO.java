package com.ohgiraffers.springjpa.menu.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {

    private int categoryCode;

    private String categoryName;

    private int refCategoryCode;

}
