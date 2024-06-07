package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class LikedMemberNo {

    @Column(name = "liked_member_no")
    private String LikedMemberNo;

    protected LikedMemberNo() {}

    public LikedMemberNo(String likedMemberNo) {
        this.LikedMemberNo = likedMemberNo;
    }

}
