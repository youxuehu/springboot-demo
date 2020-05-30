package com.example.controller;

import com.example.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {

    @Autowired
    private SendService sendService;

    @GetMapping("/send")
    @ResponseBody
    public String send(String message) {
        return sendService.send(message);
    }
}
