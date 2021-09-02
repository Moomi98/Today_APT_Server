package com.example.today_apt_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APTInfo {

    private Long price; // 거래금액
    private int year; // 거래년도
    private String roadAddress; // 도로명주소
    private String dong; // 법정동
    private String aptName;
    private int month; // 거래월
    private int day; // 거래일
    private double area; // 전용면적
    private int floor; // 층

}
