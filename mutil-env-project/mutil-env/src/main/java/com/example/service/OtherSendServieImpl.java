package com.example.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class OtherSendServieImpl implements SendService {
    @Override
    public String send(String message) {
        return "get prod " + message;
    }
}
