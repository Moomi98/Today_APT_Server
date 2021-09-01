package com.example.today_apt_server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.yaml.snakeyaml.util.UriEncoder;

import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class ApiControllerTest {

    @Test
    public void getOpenApiTest() throws UnsupportedEncodingException, URISyntaxException {
        String encodedServiceKey = "AAU8XVY6qAEr%2BjeoQWSx5%2BDtoZilWGKXT8jlz00LhC%2BnD51sqLyQcMnaT06waub%2Fuy1OoEhGkIB4MXUpZ3qi9A%3D%3D";
        String pageNo = "1";
        String numOfRows = "10";
        String LAWD_CD = "11110";
        String DEAL_YMD = "201512";
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
        System.out.println(uri);
        System.out.println(result);
    }
}
