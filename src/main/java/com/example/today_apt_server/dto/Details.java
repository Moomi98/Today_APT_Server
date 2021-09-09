package com.example.today_apt_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details{
    private int year; // 거래년도
    private int month; // 거래월
    private int day; // 거래일
    private String roadAddress; // 도로명주소
    private String address; // 법정동
    private double area; // 전용면적
    private int floor; // 층
}