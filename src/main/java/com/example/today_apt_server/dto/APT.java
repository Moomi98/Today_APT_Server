package com.example.today_apt_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APT implements Comparable<APT>{

    private int rank; // 순위
    private String aptName; // 단지명
    private long longPrice; // 거래금액(만원)
    private String price; // 거래금액(, 첨부)

    private Details details = new Details();

    @Override
    public int compareTo(APT o) {
        return Long.compare(o.getLongPrice(), this.longPrice);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Details{
        private String address; // 시군구
        private String roadAddress; // 도로명 주소
        private double area; // 전용 면적
        private String yearMonth; // 계약년월
        private String day; // 계약일
        private int floor; // 층
    }
}
