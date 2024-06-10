package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CartDTO {

    private int cartOwnerMemberNo;

    private int addedBookNo;

    private int quantity;
}
