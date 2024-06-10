package com.ohgiraffers.mapping.section03.compositekey.subsection02.idclass;

public class CartCompositeKey {

    private int cartOwner;

    private int addedBook;

    protected CartCompositeKey() {}

    public CartCompositeKey(int cartOwner, int addedBook) {
        this.cartOwner = cartOwner;
        this.addedBook = addedBook;
    }

}
