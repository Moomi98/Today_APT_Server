package com.example.today_apt_server.csv;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Handler {

    private final CSVHandler csvHandler;
    private final PublicApiHandler publicApiHandler;

}
