package com.example.springbootdemo.controller.k8s;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/api")
public class K8sController {

    private static final Logger LOGGER = LoggerFactory.getLogger(K8sController.class);

    public void api() {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            String fileName = "classpath:/k8s/kubectl.kubeconfig";
            InputStream inputStream =  resourceLoader.getResource(fileName).getInputStream();
            ApiClient client = Config.fromConfig(inputStream);
            client.setConnectTimeout(5 * 60 * 1000);
            Configuration.setDefaultApiClient(client);
            CoreV1Api api = new CoreV1Api();
            V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
            for (V1Pod item : list.getItems()) {
                System.out.println(item.getMetadata().getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}
