package com.example.today_apt_server.data;

import com.example.today_apt_server.dto.ResAptInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Component
@Data
public class AptData {

    private ArrayList<ResAptInfo> aptInfo;
    private ArrayList<String> aptInfoJson;
}
