package com.example.today_apt_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APTInfo implements Comparable<APT>{

    int rank; // 순위
    private Long longPrice; // 거래금액
    private String price; // 실제 전송할 거래 금액
    private String aptName; // 아파트명

    @Override
    public int compareTo(APT o) {
        return Long.compare(o.getLongPrice(), this.longPrice);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Details{
        private int year; // 거래년도
        private String roadAddress; // 도로명주소
        private String dong; // 법정동
        private int month; // 거래월
        private int day; // 거래일
        private double area; // 전용면적
        private int floor; // 층
    }
}
