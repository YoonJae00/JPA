package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;

@Embeddable
public class LikedCompositeKey {

    @Embedded
    private LikedMemberNo memberNo;

    @Embedded
    private LikedBookNo likedBookNo;

    protected LikedCompositeKey() {}

    public LikedCompositeKey(LikedMemberNo memberNo, LikedBookNo likedBookNo) {
        this.memberNo = memberNo;
        this.likedBookNo = likedBookNo;
    }
}
