package org.example.test.impl;

import org.example.test.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public String message(String message) {
        return "hello, " + message;
    }

    @Override
    public String toString() {
        return "TestServiceImpl";
    }
}
