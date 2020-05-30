package com.example.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class SendServiceImpl implements SendService {
    @Override
    public String send(String message) {
        return "get dev " + message;
    }
}
