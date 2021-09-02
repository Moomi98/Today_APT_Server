package com.example.today_apt_server.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@Component
public class PublicApi {

    public String getOpenApi(String LAWD_CD) throws URISyntaxException {
        String encodedServiceKey = "AAU8XVY6qAEr%2BjeoQWSx5%2BDtoZilWGKXT8jlz00LhC%2BnD51sqLyQcMnaT06waub%2Fuy1OoEhGkIB4MXUpZ3qi9A%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String DEAL_YMD = "201912";
        String apiUri = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        String stringUri = UriComponentsBuilder.fromUriString(apiUri)
                .queryParam("serviceKey", encodedServiceKey)
                .queryParam("pageNo", pageNo)
                .queryParam("numOfRows", numOfRows)
                .queryParam("LAWD_CD", LAWD_CD)
                .queryParam("DEAL_YMD", DEAL_YMD)
                .build(false).toString();
        URI uri = new URI(stringUri); // URI 클래스 사용 시 한 번 encoding 작업 후 get 요청

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();


        return result;
    }

    public String getAreaCode() throws IOException, URISyntaxException { // 법정동코드 파일의 존재하는 모든 구,군 단위의 법정동 코드 전처리
        File file = new File("./data/법정동코드 전체자료.txt");
        if(file.exists()){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String str;
            while((str = bufferedReader.readLine()) != null){
                var text = str.split("\\s");
                if(text[text.length - 1].equals("존재") && text.length == 4){
                    return text[0].substring(0, 5);
                }
                // System.out.println(text[0].substring(0, 5) + " " + text[1] + " " + text[2] + " " + text[3]);
            }
        }
        else
            System.out.println("파일이 없습니다.");

        return "";
    }
}
