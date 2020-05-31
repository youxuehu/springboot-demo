package com.example.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

public class Operate {
    @Autowired
    RestHighLevelClient highLevelClient;

    public void op() {
        RestClient lowLevelClient = highLevelClient.getLowLevelClient();
        IndexRequest request = new IndexRequest("spring-data", "elasticsearch", UUID.randomUUID().toString())
                .source(singletonMap("feature", "high-level-rest-client"))
                .setRefreshPolicy(IMMEDIATE);
        new RequestOptions()
        IndexResponse response = highLevelClient.index(request);
    }
}
