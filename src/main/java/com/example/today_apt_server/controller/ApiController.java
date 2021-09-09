package com.example.today_apt_server.controller;

import com.example.today_apt_server.handler.Handler;
import com.example.today_apt_server.dto.APT;
import com.example.today_apt_server.handler.PublicApiHandler;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {
    private Handler handler;

    @GetMapping("/get/aptInfo")
    public void getAptInfo(){

    }

}
