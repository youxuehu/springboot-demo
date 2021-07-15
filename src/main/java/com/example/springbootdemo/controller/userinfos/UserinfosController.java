package com.example.springbootdemo.controller.userinfos;

import com.example.common.db.service.userinfos.UserinfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserinfosController {

    @Autowired
    UserinfosService userinfosService;

    @GetMapping("/insertUserinfos")
    public List<Integer> insertUserinfos() {
        return userinfosService.insertUserinfos();
    }
}
