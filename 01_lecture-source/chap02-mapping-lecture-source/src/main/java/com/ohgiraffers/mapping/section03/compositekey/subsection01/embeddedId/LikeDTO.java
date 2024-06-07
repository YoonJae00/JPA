package com.ohgiraffers.mapping.section03.compositekey.subsection01.embeddedId;


import lombok.*;

public class LikeDTO {

    private int likedMemberNo;

    private int likedBookNo;

    protected LikeDTO() {}

    public LikeDTO(int likedMemberNo, int likedBookNo) {
        this.likedMemberNo = likedMemberNo;
        this.likedBookNo = likedBookNo;
    }

    public int getLikedMemberNo() {
        return likedMemberNo;
    }

    public void setLikedMemberNo(int likedMemberNo) {
        this.likedMemberNo = likedMemberNo;
    }

    public int getLikedBookNo() {
        return likedBookNo;
    }

    public void setLikedBookNo(int likedBookNo) {
        this.likedBookNo = likedBookNo;
    }

    @Override
    public String toString() {
        return "LikeDTO{" +
                "likedMemberNo=" + likedMemberNo +
                ", likedBookNo=" + likedBookNo +
                '}';
    }
}
