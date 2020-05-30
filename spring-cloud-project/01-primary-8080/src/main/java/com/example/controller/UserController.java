package com.example.controller;

import com.example.param.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static final String HOST_URL = "http://localhost:8081";

    @PostMapping
    public ResponseEntity<Boolean> save(@RequestBody User user) {
        return restTemplate.postForEntity(HOST_URL + "/user/", user, Boolean.class);
    }

    @PutMapping
    public void update(@RequestBody User user) {
        restTemplate.put(HOST_URL + "/user/", user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id) {
        restTemplate.delete(HOST_URL + "/user/" + id);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Integer id) {
        return restTemplate.getForEntity(HOST_URL + "/user/" + id, User.class);
    }

    @GetMapping
    public ResponseEntity<List> listAll() {
        return restTemplate.getForEntity(HOST_URL + "/user/", List.class);
    }
}
