package com.example.today_apt_server;

import com.example.today_apt_server.data.AptData;
import com.example.today_apt_server.handler.Handler;
import com.example.today_apt_server.handler.PublicApiHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class TodayAptServerApplication {

    public static void main(String[] args) throws URISyntaxException, IOException {
        SpringApplication.run(TodayAptServerApplication.class, args);
        AptData aptData = new AptData();
        Handler handler = new Handler(new PublicApiHandler());
        PublicApiHandler publicApiHandler = handler.getPublicApiHandler();
        aptData.setAptInfoJson(publicApiHandler.getOpenApi("201912"));
        System.out.println("데이터 작업 완료");
    }
}
