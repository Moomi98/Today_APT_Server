package com.example.today_apt_server.handler;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class Handler {

    private final PublicApiHandler publicApiHandler;

}
