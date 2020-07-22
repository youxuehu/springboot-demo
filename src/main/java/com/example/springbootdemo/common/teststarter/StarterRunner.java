//package com.example.springbootdemo.common.teststarter;
//
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.cluster.node.DiscoveryNode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Slf4j
//@Service
//public class StarterRunner implements ApplicationRunner {
//
//    @Autowired
//    private TransportClient transportClient;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        List<DiscoveryNode> discoveryNodes = transportClient.listedNodes();
//        log.info("{}", discoveryNodes);
//    }
//}
