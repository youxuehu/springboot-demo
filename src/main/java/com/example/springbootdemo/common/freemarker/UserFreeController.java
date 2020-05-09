package com.example.springbootdemo.common.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserFreeController {

    @GetMapping("/indexFree")
    public String index(Model model) {

        List<UserFree> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserFree user = new UserFree();
            user.setId((long) i);
            user.setUsername("javaboy>>>>" + i);
            user.setAddress("www.javaboy.org>>>>" + i);
            users.add(user);
        }
        model.addAttribute("users", users);
        return "indexFree";
    }
}