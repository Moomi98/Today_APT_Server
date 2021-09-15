package com.example.today_apt_server.controller;

import com.example.today_apt_server.ApplicationContextProvider;
import com.example.today_apt_server.converter.Converter;
import com.example.today_apt_server.dto.ResAptInfo;
import com.example.today_apt_server.handler.PublicApiHandler;
import com.example.today_apt_server.handler.SearchHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("get/search")
    public ArrayList<String> searchAptInfo(@RequestParam(value = "aptName", required = false, defaultValue = "") String aptName) throws JsonProcessingException {
        ApplicationContext context = ApplicationContextProvider.getContext();
        SearchHandler searchHandler = context.getBean(SearchHandler.class);
        var result = searchHandler.searchApt(aptName);

        return Converter.convertToJson(result);
    }

}
