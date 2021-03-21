package com.example.springbootdemo.common.k8sclient;


import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.PodLogs;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Namespace;
import io.kubernetes.client.models.V1ObjectMeta;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;


public class KubernetesClientJavaExample {
    public static void main(String[] args) throws Exception {

        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        CoreV1Api api = new CoreV1Api();
        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
        System.out.println("k8s namespace list:");
        for (V1Pod item : list.getItems()) {
            System.out.println(item.getMetadata().getName());
        }
        /**
         * coredns-f9fd979d6-s88h6
         * coredns-f9fd979d6-wgclf
         * etcd-docker-desktop
         * kube-apiserver-docker-desktop
         * kube-controller-manager-docker-desktop
         * kube-proxy-zqm2w
         * kube-scheduler-docker-desktop
         * storage-provisioner
         * vpnkit-controller
         * dashboard-metrics-scraper-7b59f7d4df-mjhh2
         * kubernetes-dashboard-74d688b6bc-vh5sj
         */

//        V1Namespace v1Namespace = new V1Namespace();
//        v1Namespace.setApiVersion("apps/v1");
//        v1Namespace.setKind("Deployment");
//
//        V1ObjectMeta v1ObjectMeta = new V1ObjectMeta();
//        v1ObjectMeta.setNamespace("tiger");
//        v1ObjectMeta.setName("tiger");
//        v1Namespace.setMetadata(v1ObjectMeta);
//
//
//        V1Namespace namespace = api.createNamespace(v1Namespace, true, null, null);
//        System.out.println(namespace);

        PodLogs logs = new PodLogs();
        V1Pod pod = api.listNamespacedPod("default", false, null, null, null, null,
                null, null, null, null)
                        .getItems()
                        .get(0);
        System.out.println("k8s pod list: ");
        System.out.println(pod);

//        InputStream is = logs.streamNamespacedPodLog(pod);

    }


}
