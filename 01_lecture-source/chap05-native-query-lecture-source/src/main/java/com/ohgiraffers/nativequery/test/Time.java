package com.ohgiraffers.nativequery.test;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Time {

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;
}
