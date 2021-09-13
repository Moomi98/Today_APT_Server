package com.example.today_apt_server.controller;

import com.example.today_apt_server.ApplicationContextProvider;
import com.example.today_apt_server.handler.PublicApiHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {



    @GetMapping("/get/aptInfo")
    public List<String> getAptInfo(){
        ApplicationContext context = ApplicationContextProvider.getContext();
        PublicApiHandler publicApiHandler = context.getBean(PublicApiHandler.class);
        return  publicApiHandler.getAptInfoJson().subList(0, 20); // 상위 20개 항목만 반환
    }

}
