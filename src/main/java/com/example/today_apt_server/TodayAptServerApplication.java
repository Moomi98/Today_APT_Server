package com.example.today_apt_server;

import com.example.today_apt_server.handler.PublicApiHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@SpringBootApplication
public class TodayAptServerApplication {

    public static void main(String[] args) throws URISyntaxException, IOException {
        SpringApplication.run(TodayAptServerApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();
        PublicApiHandler publicApiHandler = context.getBean(PublicApiHandler.class);

        ArrayList<String> aptInfoJson = publicApiHandler.getOpenApi("201912");
        System.out.println("데이터 작업 완료");
    }
}
