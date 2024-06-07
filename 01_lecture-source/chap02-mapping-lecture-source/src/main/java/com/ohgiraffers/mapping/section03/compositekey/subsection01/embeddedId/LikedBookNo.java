package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LikedBookNo {

    @Column(name = "liked_book_no")
    private int likedBookNo;

    protected LikedBookNo() {}

    public LikedBookNo(int likedBookNo) {
        this.likedBookNo = likedBookNo;
    }
}
