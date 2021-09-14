package com.example.today_apt_server.handler;

import com.example.today_apt_server.ApplicationContextProvider;
import com.example.today_apt_server.converter.Converter;
import com.example.today_apt_server.dto.APT;
import com.example.today_apt_server.dto.Details;
import com.example.today_apt_server.dto.ReqAPTInfo;
import com.example.today_apt_server.dto.ResAptInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Component
@Data
public class PublicApiHandler {

    private ArrayList<ReqAPTInfo> reqAPTInfoArrayList;
    private ArrayList<ResAptInfo> resAptInfoArrayList;
    private ArrayList<String> aptInfoJson;
    private HashMap<String, String> codeMap;
    private HashMap<String, ArrayList<ResAptInfo>> aptInfoHashMap; // 같은 이름을 가진 아파트가 여러 곳에 있을 수도 있으므로 list 로 관리

    public void getOpenApi(String DEAL_YMD) throws URISyntaxException, IOException {
        System.out.println("데이터 분석 시작...");
        ObjectMapper objectMapper = new ObjectMapper();
        reqAPTInfoArrayList = new ArrayList<>();
        String encodedServiceKey = "AAU8XVY6qAEr%2BjeoQWSx5%2BDtoZilWGKXT8jlz00LhC%2BnD51sqLyQcMnaT06waub%2Fuy1OoEhGkIB4MXUpZ3qi9A%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String apiUri = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        RestTemplate restTemplate = new RestTemplate();

        ArrayList<String> codeList = getAreaCode();

        for(String code : codeList) {
            String stringUri = UriComponentsBuilder.fromUriString(apiUri)
                    .queryParam("serviceKey", encodedServiceKey)
                    .queryParam("pageNo", pageNo)
                    .queryParam("numOfRows", numOfRows)
                    .queryParam("LAWD_CD", code)
                    .queryParam("DEAL_YMD", DEAL_YMD)
                    .build(false).toString();
            URI uri = new URI(stringUri); // URI 클래스 사용 시 한 번 encoding 작업 후 get 요청
            String result = restTemplate.getForObject(uri, String.class);

            String jsonObject = jsonProcessing(result);
            if(jsonObject != null){
                JSONArray jsonArray = new JSONArray(jsonObject);

                for(Object json : jsonArray){
                    ReqAPTInfo reqAPTInfo = objectMapper.readValue(json.toString(), ReqAPTInfo.class);
                    reqAPTInfo.setAddress(codeMap.get(code) + " " + reqAPTInfo.getDong());
                    reqAPTInfoArrayList.add(reqAPTInfo);
                }
            }
        }

        sortByRank();
        resAptInfoArrayList = reqInfoToResInfo();
        aptInfoJson = Converter.convertToJson(resAptInfoArrayList.subList(0, 50));
    }

    private String jsonProcessing(String json) throws JSONException { // 다중 json에서 item들만 골라내는 함수
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonObject1 = jsonObject.optJSONObject("response");
        JSONObject jsonObject2 = jsonObject1.optJSONObject("body");

        int totalCount = jsonObject2.getInt("totalCount"); // 해당 구의 거래 내역이 있었는지 확인
        if(totalCount != 0){
            JSONObject jsonObject3 = jsonObject2.getJSONObject("items");
            return jsonObject3.get("item").toString();
        }

        return null;
    }

    private ArrayList<String> getAreaCode() throws IOException { // 법정동코드 파일의 존재하는 모든 구,군 단위의 법정동 코드 전처리
        System.out.println("법정동 코드 전처리중...");
        ArrayList<String> codeList = new ArrayList<>(); // 법정동 코드를 담을 리스트
        codeMap = new HashMap<>();
        File file = new File("./data/법정동코드 전체자료.txt");
        if(file.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String str;
            while((str = bufferedReader.readLine()) != null){
                var text = str.split("\\s");
                if(text[text.length - 1].equals("존재") && text.length == 4){
                    codeList.add(text[0].substring(0, 5));
                    codeMap.put(text[0].substring(0, 5), text[1] + " " + text[2]);
                }
            }
        }
        else
            System.out.println("파일이 없습니다.");

        System.out.println("법정동 코드 전처리 완료!");
        return codeList;
    }

    private void sortByRank(){ // 가격을 기준으로 순위를 정렬하는 함수
        System.out.println("순위 정렬 중...");
       for(ReqAPTInfo reqAPTInfo : reqAPTInfoArrayList){
           String price = Converter.deleteComma(reqAPTInfo.getPrice().replaceAll(" ", ""));

           int intPrice = Integer.parseInt(Converter.deleteComma(price));
            reqAPTInfo.setIntPrice(intPrice);
        }
        Collections.sort(reqAPTInfoArrayList);
        System.out.println("순위 정렬 완료!");
    }

    private ArrayList<ResAptInfo> reqInfoToResInfo(){ // 공공데이터 api 로부터 받은 데이터를 웹 페이지에 전달할 데이터로 변환
        System.out.println("데이터 변환 중...");
        ArrayList<ResAptInfo> resAptInfoArrayList = new ArrayList<>();
        for(int i = 0; i < reqAPTInfoArrayList.size(); i++){
            ResAptInfo resAptInfo = new ResAptInfo();
            Details details = new Details();

            resAptInfo.setAptName(reqAPTInfoArrayList.get(i).getAptName());
            resAptInfo.setPrice(Converter.addCommaToPrice(reqAPTInfoArrayList.get(i).getPrice().replaceAll(" ", "")));
            resAptInfo.setRank(i + 1);
            details.setArea(reqAPTInfoArrayList.get(i).getArea());
            details.setAddress(reqAPTInfoArrayList.get(i).getAddress());
            details.setRoadAddress(reqAPTInfoArrayList.get(i).getRoadAddress());
            details.setYear(reqAPTInfoArrayList.get(i).getYear());
            details.setMonth(reqAPTInfoArrayList.get(i).getMonth());
            details.setDay(reqAPTInfoArrayList.get(i).getDay());
            details.setFloor(reqAPTInfoArrayList.get(i).getFloor());
            resAptInfo.setDetails(details);

            resAptInfoArrayList.add(resAptInfo);

            ArrayList<ResAptInfo> arrayList;
            try{
                arrayList = aptInfoHashMap.get(resAptInfoArrayList.get(i).getAptName());
            }
            catch (NullPointerException e){
                arrayList = new ArrayList<>();
            }
            arrayList.add(resAptInfo);
            aptInfoHashMap.put(reqAPTInfoArrayList.get(i).getAptName(), arrayList);
        }
        System.out.println("데이터 변환 완료!");
        return resAptInfoArrayList;
    }

    private void setSearchHandler(){
        ApplicationContext context = ApplicationContextProvider.getContext();
        SearchHandler searchHandler = context.getBean(SearchHandler.class);
        searchHandler.setAptInfoHashMap(aptInfoHashMap);
    }
}
