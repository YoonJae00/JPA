package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_cart")
@IdClass(CartCompositeKey.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Cart {

    @Id
    @Column(name = "cart_owner")
    private int cartOwner;

    @Id
    @Column(name = "added_book")
    private int addedBook;

    @Column(name = "quantity")
    private int quantity;



}
