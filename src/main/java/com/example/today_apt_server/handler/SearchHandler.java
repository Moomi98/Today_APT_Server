package com.example.today_apt_server.handler;

import com.example.today_apt_server.ApplicationContextProvider;
import com.example.today_apt_server.dto.ResAptInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHandler {

    private ArrayList<ResAptInfo> resAptInfoArrayList;

    public ArrayList<ResAptInfo> searchApt(String aptName){

        ArrayList<ResAptInfo> result = new ArrayList<>();

        for(ResAptInfo resAptInfo : resAptInfoArrayList){
            if(resAptInfo.getAptName().contains(aptName))
                result.add(resAptInfo);
        }

        return result;
    }
}
