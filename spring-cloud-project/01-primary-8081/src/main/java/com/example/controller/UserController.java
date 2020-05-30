package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public boolean save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @PutMapping
    public boolean update(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping("{id}")
    public User getById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @GetMapping()
    public List<User> listAll() {
        return userService.listAll();
    }
}
