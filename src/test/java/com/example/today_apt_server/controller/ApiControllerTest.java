package com.example.today_apt_server.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
public class ApiControllerTest {

    @Test
    @GetMapping("/api/get")
    public void getApiTest(){
        System.out.println("getApiTest()");
    }
}
