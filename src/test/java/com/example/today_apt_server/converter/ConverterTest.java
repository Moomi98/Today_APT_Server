package com.example.today_apt_server.converter;

import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    public void addCommaPriceTest(){
        String price = "26800";
        StringBuilder stringBuilder = new StringBuilder(price + "0000");
        for(int i = stringBuilder.length() - 3; i > 0; i -= 3){
            stringBuilder.insert(i ,',');
        }

        System.out.println(stringBuilder);

    }
}
