package org.example.test.impl;

import org.example.test.TestService;

public class TestServiceImpl implements TestService {
    @Override
    public String message(String message) {
        return "hello, " + message;
    }
}
