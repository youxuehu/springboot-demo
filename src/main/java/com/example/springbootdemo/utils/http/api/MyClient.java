package com.example.springbootdemo.utils.http.api;

import com.dtflys.forest.annotation.Request;

public interface MyClient {

    @Request(url = "https://www.baidu.com")
    String baidu();

    @Request(url = "https://www.taobao.com")
    String taobao();
}
