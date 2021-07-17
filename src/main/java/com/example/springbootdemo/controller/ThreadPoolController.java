package com.example.springbootdemo.controller;

import com.example.common.utils.file.copy.ThreadPoolMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread/pool")
public class ThreadPoolController {

    @Autowired
    private ThreadPoolMain threadPoolMain;

    @RequestMapping("copyFile")
    public void copyFile() {
        threadPoolMain.copyFile("", "", 5);
    }
}
