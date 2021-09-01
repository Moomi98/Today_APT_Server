package com.example.today_apt_server.csv;

import org.springframework.stereotype.Component;

@Component
public class Handler {

    private final CSVHandler csvHandler;


    public Handler(CSVHandler csvHandler) {
        this.csvHandler = csvHandler;
    }

    public CSVHandler getCsvHandler(){
        return csvHandler;
    }

}
