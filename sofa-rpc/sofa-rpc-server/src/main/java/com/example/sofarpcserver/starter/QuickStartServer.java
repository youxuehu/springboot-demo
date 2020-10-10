package com.example.sofarpcserver.starter;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.example.sofarpcserver.service.impl.HelloServiceImpl;
import org.example.service.HelloService;

public class QuickStartServer {

    public static void main(String[] args) {

        ServerConfig serverConfig = new ServerConfig()
                .setProtocol("bolt")
                .setPort(12200)
                .setDaemon(false);
        ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
                .setInterfaceId(HelloService.class.getName())
                .setRef(new HelloServiceImpl())
                .setServer(serverConfig);
        providerConfig.export();
    }
}
