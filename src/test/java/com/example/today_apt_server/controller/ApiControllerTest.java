package com.example.today_apt_server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

@SpringBootTest
public class ApiControllerTest {

    @Test
    public void getOpenApiTest() throws UnsupportedEncodingException {
        String serviceKey = "AAU8XVY6qAEr+jeoQWSx5+DtoZilWGKXT8jlz00LhC+nD51sqLyQcMnaT06waub/uy1OoEhGkIB4MXUpZ3qi9A==";
        //String decodedServiceKey = URLDecoder.decode(serviceKey,"UTF-8");
       // URI serviceKeyUri = UriComponentsBuilder.fromUriString(serviceKey).build().toUri();
        String apiUri = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev?LAWD_CD=11110&DEAL_YMD=201512&serviceKey=" + serviceKey;
        URI uri = UriComponentsBuilder.fromUriString(apiUri).encode().build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(apiUri);
        System.out.println(result);
    }
}
