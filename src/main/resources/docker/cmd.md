# 停止所有的容器
docker stop $(docker ps -a -q)

# 删除所有的容器
docker rm $(docker ps -a -q)

# 查看所有镜像
docker images

# 删除指定id的镜像
docker rmi <image id>

# 删除全部的images
docker rmi $(docker images -q)
# 强制删除
docker rmi -f $(docker images -q)

# 删除untagged images
docker rmi $(docker images | grep "^" | awk "{print $3}")

# mysql run
docker run -it -m 500M --memory-reservation 200M --restart=always  -p 3306:3306 --name youxuehu-mysql -v $PWD/conf:/etc/mysql/conf.d -v $PWD/logs:/logs -v $PWD/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=zhf123.. -d mysql:latest
ALTER user 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'zhf123..';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'zhf123..' WITH GRANT OPTION;