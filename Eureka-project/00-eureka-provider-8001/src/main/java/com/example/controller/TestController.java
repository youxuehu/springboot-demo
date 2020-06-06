package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping
    public String test(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return "hello, " + url;
    }

    @GetMapping("discovery")
    public void discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                String host = instance.getHost();
                String instanceId = instance.getInstanceId();
                int port = instance.getPort();
                URI uri = instance.getUri();
                String serviceId = instance.getServiceId();
                String scheme = instance.getScheme();
                Map<String, String> metadata = instance.getMetadata();
                System.out.println("instanceId:" + instanceId);
                System.out.println("serviceId:" + serviceId);
                System.out.println("host:" + host);
                System.out.println("port:" + port);
                System.out.println("uri:" + uri.toString());
                System.out.println("scheme:" + scheme);
                System.out.println("metadata:" + metadata);
            }
        }
    }
}
