package com.example.springbootdemo.utils.http;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class HttpRequestParams {
    private String url;
    private Map<String, String> params;
    private Map<String, String> heads;
}
