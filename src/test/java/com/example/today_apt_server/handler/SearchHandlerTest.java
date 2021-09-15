package com.example.today_apt_server.handler;

import com.example.today_apt_server.dto.ResAptInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest

public class SearchHandlerTest {

    private ArrayList<ResAptInfo> resAptInfoArrayList;

    public void setResAptInfoArrayList(ArrayList<ResAptInfo> resAptInfoArrayList){
        this.resAptInfoArrayList = resAptInfoArrayList;
    }

    public void searchTest(String aptName){

        ArrayList<ResAptInfo> result = new ArrayList<>();

        for(ResAptInfo resAptInfo : resAptInfoArrayList){
            if(resAptInfo.getAptName().contains(aptName))
                result.add(resAptInfo);
        }

        System.out.println(result);

    }
}
