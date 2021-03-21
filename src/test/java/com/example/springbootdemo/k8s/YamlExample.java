package com.example.springbootdemo.k8s;

import com.google.gson.GsonBuilder;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Namespace;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.models.V1ReplicationController;
import io.kubernetes.client.models.V1Service;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.Yaml;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.util.Date;

public class YamlExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(YamlExample.class);

    /**
     * 本次实战用到的namespace，和namespace.yaml、service.yaml文件中的一致
     */
    private final static String NAMESPACE = "yaml";

    /**
     * 本次实战用到的service的名称，和service.yaml文件中的一致
     */
    private final static String SERVICE_NAME = "test-service";


    /**
     * 通过 api client 提交yaml任务
     * @throws Exception
     */
    @Test
    public void yaml() throws Exception {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();
        // 通过yaml文件创建namespace实例
        V1Namespace namespace = (V1Namespace) Yaml.load(new ClassPathResource("namespace.yaml").getFile());

        // 创建namespace资源
        api.createNamespace(namespace, null, null, null);

        // 通过yaml文件创建service实例
        V1Service service = (V1Service) Yaml.load(new ClassPathResource("service.yaml").getFile());

        // 创建service资源
        api.createNamespacedService(NAMESPACE, service, null, null, null);

        String result =  "load operation success " + new Date();
        System.out.println(result);
    }

    /**
     * kubectl create -f redis.yaml
     * @throws Exception
     */
    @Test
    public void service() throws Exception {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        // 通过yaml文件创建service实例
        V1ReplicationController service = (V1ReplicationController) Yaml.load(new ClassPathResource("redis.yaml").getFile());

        // 创建service资源
        V1ReplicationController redisService = api.createNamespacedReplicationController(NAMESPACE, service, null, null, null);
        LOGGER.info("pod info \n{}", new GsonBuilder().setPrettyPrinting().create().toJson(redisService));
    }

    @Test
    public void log() throws Exception {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api api = new CoreV1Api();

        // String name, String namespace, String container, Boolean follow, Integer limitBytes, String pretty,
        // Boolean previous, Integer sinceSeconds, Integer tailLines, Boolean timestamps
        String logs = api.readNamespacedPodLog("redis-rdhj8", NAMESPACE, "redis-rdhj8", true,
                null, null, true, null, null, null);
        LOGGER.info("logs: {}", logs);
    }

    @Test
    public void pod() throws ApiException, IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);
        CoreV1Api apiInstance = new CoreV1Api();

        // String | If 'true', then the output is pretty printed.
        String pretty = null;

        // 订阅事件相关的参数，这里用不上
        Boolean allowWatchBookmarks = false;

        // 连续查找的标志，类似于翻页
        String _continue = null;

        //  字段选择器
        String fieldSelector = "status.phase=Running";

        // 根据标签过滤
        // String labelSelector = "component=kube-apiserver";
        String labelSelector = null;

        Integer limit = null;
        String resourceVersion = null;
        Integer timeoutSeconds = null;
        Boolean watch = false;

       // String namespace, Boolean includeUninitialized, String pretty, String _continue, String fieldSelector,
       //         String labelSelector, Integer limit, String resourceVersion, Integer timeoutSeconds, Boolean watch)
        V1PodList v1PodList = apiInstance.listNamespacedPod(NAMESPACE, allowWatchBookmarks,
                pretty,
                _continue,
                fieldSelector,
                labelSelector,
                limit,
                resourceVersion,
                timeoutSeconds,
                watch);

        // 使用Gson将集合对象序列化成JSON，在日志中打印出来
        LOGGER.info("pod info \n{}", new GsonBuilder().setPrettyPrinting().create().toJson(v1PodList));

    }
}
