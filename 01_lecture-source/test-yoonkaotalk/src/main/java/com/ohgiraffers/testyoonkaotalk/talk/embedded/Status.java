package com.ohgiraffers.testyoonkaotalk.talk.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Column(name = "status")
//    @ColumnDefault("'Y'")
    private String status;
}
