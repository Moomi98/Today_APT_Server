package com.example.today_apt_server.handler;

import com.example.today_apt_server.dto.ResAptInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
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
