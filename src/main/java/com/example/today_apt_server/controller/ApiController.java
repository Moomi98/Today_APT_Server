package com.example.today_apt_server.controller;

import com.example.today_apt_server.csv.CSVHandler;
import com.example.today_apt_server.csv.Handler;
import com.example.today_apt_server.dto.APT;
import com.example.today_apt_server.converter.Converter;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {
    private Handler handler;
    private CSVHandler csvHandler;

    @GetMapping("/get/aptInfo")
    public ArrayList<String> sendTodayAptInfo() throws IOException {
        handler = new Handler(new CSVHandler());
        csvHandler =
        ArrayList<APT> aptList = csvHandler.openCSV("C:\\Today_APT_Server\\data\\data_20210731.csv");

        return Converter.convertToJson(aptList);
    }

    @GetMapping("/get/search")
    public APT sendRequestedAptInfo(@RequestParam String aptName) throws IOException {

        return csvHandler.findAptFromData(aptName);
    }

}
