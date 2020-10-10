package com.example.sofarpcclient.starter;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import org.example.service.HelloService;

public class QuickStartClient {

    public static void main(String[] args) {

        ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())
                .setProtocol("bolt")
                .setDirectUrl("bolt://127.0.0.1:12200");

        HelloService helloService = consumerConfig.refer();

        while (true) {
            System.out.println(helloService.sayHello("jack"));
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
