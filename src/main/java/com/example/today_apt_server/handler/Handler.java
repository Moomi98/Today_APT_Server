package com.example.today_apt_server.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Handler {

    private final PublicApiHandler publicApiHandler;

}
