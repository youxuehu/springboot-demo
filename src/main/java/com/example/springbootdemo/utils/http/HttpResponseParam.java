package com.example.springbootdemo.utils.http;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class HttpResponseParam {
    private int statusCode;

    private String body;
}
