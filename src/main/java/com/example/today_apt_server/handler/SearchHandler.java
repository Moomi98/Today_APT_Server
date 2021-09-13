package com.example.today_apt_server.handler;

import com.example.today_apt_server.ApplicationContextProvider;
import com.example.today_apt_server.dto.ResAptInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

import javax.print.attribute.HashPrintJobAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHandler {

    private HashMap<String, ArrayList<ResAptInfo>> aptInfoHashMap;

    public void searchApt(String aptName){}
}
