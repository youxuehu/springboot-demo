第一步 克隆详细

git clone https://github.com/gotok8s/k8s-docker-desktop-for-mac.git

第二步 进入 k8s-docker-desktop-for-mac项目，拉取镜像

./load_images.sh

第三步 打开docker 配置页面，enable k8s。需要等k8s start一会


验证

    $ kubectl cluster-info
    $ kubectl get nodes
    $ kubectl describe node



部署 Kubernetes Dashboard

    kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/master/aio/deploy/recommended.yaml
    #开启本机访问代理
    kubectl proxy



创建Dashboard管理员用户并用token登陆

    # 创建 ServiceAccount kubernetes-dashboard-admin 并绑定集群管理员权限
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml
    
    # 获取登陆 token
    kubectl -n kubernetes-dashboard describe secret $(kubectl -n kubernetes-dashboard get secret | grep kubernetes-dashboard-admin | awk '{print $1}')


通过下面的连接访问 Dashboard: 

    http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/

