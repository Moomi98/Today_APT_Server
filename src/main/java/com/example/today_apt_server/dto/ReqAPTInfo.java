package com.example.today_apt_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqAPTInfo implements Comparable<ReqAPTInfo>{

    @JsonProperty("거래금액")
    private Long longPrice; // 거래금액
    @JsonProperty("아파트")
    private String aptName; // 아파트명
    @JsonProperty("년")
    private int year; // 거래년도
    @JsonProperty("사직로")
    private String roadAddress; // 도로명주소
    @JsonProperty("법정동")
    private String dong; // 법정동
    @JsonProperty("월")
    private int month; // 거래월
    @JsonProperty("일")
    private int day; // 거래일
    @JsonProperty("전용면적")
    private double area; // 전용면적
    @JsonProperty("층")
    private int floor; // 층


    @Override
    public int compareTo(ReqAPTInfo o) {
        return Long.compare(o.getLongPrice(), this.longPrice);
    }
}
