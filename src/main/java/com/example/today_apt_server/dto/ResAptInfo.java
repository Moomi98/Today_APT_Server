package com.example.today_apt_server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResAptInfo {

    int rank; // 순위
    private String price; // 실제 전송할 거래 금액
    private String aptName; // 아파트명
    private Details details = new Details(); // 세부 정보

}

