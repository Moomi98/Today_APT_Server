package com.example.today_apt_server.converter;

import com.example.today_apt_server.dto.APT;
import com.example.today_apt_server.dto.ResAptInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {

    public static ArrayList<String> convertToJson(List<ResAptInfo> aptList) throws JsonProcessingException {
        ArrayList<String> jsonList = new ArrayList<>();
        var objectMapper = new ObjectMapper();
        for(ResAptInfo apt : aptList){
            String info = objectMapper.writeValueAsString(apt);
            jsonList.add(info);
        }

        System.out.println(jsonList);
        return jsonList;
    }

    public static String addCommaToPrice(String price){ // 가격을 보기 편하게 콤마를 붙여주는 함수
        StringBuilder stringBuilder = new StringBuilder(price + "0000");
        for(int i = stringBuilder.length() - 3; i > 0; i -= 3){
            stringBuilder.insert(i ,',');
        }

        return stringBuilder.toString();
    }

    // 숫자에 붙어있는 콤마를 전부 없애주는 함수
    public static String deleteComma(String price){
        return price.replaceAll(",", "");
    }


}
