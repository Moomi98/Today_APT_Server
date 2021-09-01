package com.example.today_apt_server.csv;

import com.example.today_apt_server.converter.Converter;
import com.example.today_apt_server.dto.APT;
import com.opencsv.CSVReader;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component // 스프링부트에서 싱글톤 형태로 객체를 생성하여 관리
@Getter
public class CSVHandler {

    private ArrayList<APT> aptList;
    Map<String, APT> aptMap;

    public ArrayList<APT> openCSV(String filePath) throws IOException {
        CSVReader csvReader = new CSVReader(new InputStreamReader( new FileInputStream(filePath), "euc-kr"));
        String [] nextLine;
        aptList = new ArrayList<>(); // aptList 생성

        for(int i = 0; i < 16; i++){ // csv 기본 설명 정보 15줄 건너뛰기
            csvReader.readNext();
        }

        while ((nextLine = csvReader.readNext()) != null) { // csv 모든 내용 출력
            APT apt = new APT();
            for (int i = 0; i < nextLine.length; i++) {
                getAptList(apt, nextLine[i], i); // 정보를 종류 별로 객체에 저장
            }
            aptList.add(apt);
        }

        setRanking(aptList);
        convertListToMap(aptList);
        System.out.println("----------- 분석 완료! ---------------");

        return aptList;
    }

    public APT findAptFromData(String text){
        APT apt;
        System.out.println(aptMap);
        var keySet = aptMap.keySet();

        for(String key : keySet){
            if (key.contains(text)){
                apt = aptMap.get(key);
                return apt;
            }
        }
        return null;
    }

    private void convertListToMap(ArrayList<APT> aptList){ // list 형태의 aptInfo -> map 으로 변환
        aptMap = new HashMap<>();
        APT apt1;

        for(APT apt : aptList){
            apt1 = apt;
            aptMap.put(apt.getAptName(), apt1);
            System.out.println(aptMap.get(apt.getAptName()));
        }
    }

    private void getAptList(APT apt, String aptInfo, int index){

        switch (index){
            case 0 : {
                apt.getDetails().setAddress(aptInfo);
                break;
            }
            case 4 : {
                apt.setAptName(aptInfo);
                break;
            }
            case 5 : {
                apt.getDetails().setArea(Double.parseDouble(aptInfo));
                break;
            }
            case 6 : {
                apt.getDetails().setYearMonth(aptInfo);
                break;
            }
            case 7 : {
                apt.getDetails().setDay(aptInfo);
                break;
            }
            case 8 : {

                String newInfo = aptInfo.replace(",","");

                int intPrice = Integer.parseInt(newInfo);
                apt.setLongPrice(intPrice * 10000L);
                apt.setPrice(Converter.addCommaToPrice(newInfo + "0000"));
                break;
            }
            case 9 : {
                apt.getDetails().setFloor(Integer.parseInt(aptInfo));
                break;
            }
            case 11 : {
                apt.getDetails().setRoadAddress(aptInfo);
                break;
            }
        }
    }

    private void setRanking(ArrayList<APT> aptArrayList) { // 순위를 가격을 기준으로 결정하는 함수
        Collections.sort(aptArrayList);

        for (int i = 1; i < aptArrayList.size() + 1; i++) {
            aptArrayList.get(i - 1).setRank(i);
        }
    }
}
