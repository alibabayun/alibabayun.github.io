---
layout: post
title: "Docker 常用命令"
keywords: ["Docker 常用命令"]
description: "Docker 常用命令"
category: "Docker"
tags: ["Docker"]
---
{% include JB/setup %}


### 进入一个容器
```
docker exec -it 3646c01f6585   /bin/bash
```

1、查看容器的root用户密码

```
docker logs <容器名orID> 2>&1 | grep '^User: ' | tail -n1
```
因为Docker容器启动时的root用户的密码是随机分配的。所以，通过这种方式就可以得到redmine容器的root用户的密码了。

-----------------------
2、查看容器日志

```
docker logs -f <容器名orID>
```

-----------------------
3、查看正在运行的容器

```
docker ps
docker ps -a为查看所有的容器，包括已经停止的。
```

----------------------
4、删除所有容器

```
docker rm $(docker ps -a -q)
```
删除单个容器
```
docker rm <容器名orID>
```

------------------------
5、停止、启动、杀死一个容器

```
docker stop <容器名orID>
docker start <容器名orID>
docker kill <容器名orID>
```

-------------------------
6、查看所有镜像

```
docker images
```

-------------------------
7、删除所有镜像

```
docker rmi $(docker images | grep none | awk '{print $3}' | sort -r)
```

-------------------------
8、运行一个新容器，同时为它命名、端口映射、文件夹映射。以redmine镜像为例

```
docker run --name redmine -p 9003:80 -p 9023:22 -d -v /var/redmine/files:/redmine/files -v /var/redmine/mysql:/var/lib/mysql sameersbn/redmine
```

-------------------------
9、一个容器连接到另一个容器

```
docker run -i -t --name sonar -d -link mmysql:db   tpires/sonar-server
sonar
```
容器连接到mmysql容器，并将mmysql容器重命名为db。这样，sonar容器就可以使用db的相关的环境变量了。

-------------------------
10、拉取镜像

```
docker pull <镜像名:tag>
```
如：

```
docker pull sameersbn/redmine:latest
```

-------------------------
11、当需要把一台机器上的镜像迁移到另一台机器的时候，需要保存镜像与加载镜像。

机器a

```
docker save busybox-1 > /home/save.tar
```
使用scp将save.tar拷到机器b上，然后：

```
docker load < /home/save.tar
```

--------------------------
12、构建自己的镜像

```
docker build -t <镜像名> <Dockerfile路径>
```
如Dockerfile在当前路径：

```
docker build -t xx/gitlab .
```

--------------------------
13、重新查看container的stdout

```
# 启动top命令，后台运行
$ ID=$(sudo docker run -d ubuntu /usr/bin/top -b)
# 获取正在running的container的输出
$ sudo docker attach $ID
top - 02:05:52 up  3:05,  0 users,  load average: 0.01, 0.02, 0.05
Tasks:   1 total,   1 running,   0 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.1%us,  0.2%sy,  0.0%ni, 99.7%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:    373572k total,   355560k used,    18012k free,    27872k buffers
Swap:   786428k total,        0k used,   786428k free,   221740k cached
^C$
$ sudo docker stop $ID
```

----------------------------
14、后台运行(-d)、并暴露端口(-p)

```
docker run -d -p 127.0.0.1:33301:22 centos6-ssh
```

----------------------------
15、从Container中拷贝文件出来

```
sudo docker cp 7bb0e258aefe:/etc/debian_version .
```
拷贝7bb0e258aefe中的/etc/debian_version到当前目录下。

`注意：`只要7bb0e258aefe没有被删除，文件命名空间就还在，可以放心的把exit状态的container的文件拷贝出来

----------------------------
16. dockercloud国内镜像仓库

Push 命令:

```
docker tag <your-image> daocloud.io/<your-user-name>/<your-image>:<tag>
docker push daocloud.io/<your-user-name>/<your-image>:<tag>
```
Push 镜像前请先登录: `docker login daocloud.io`



#### 将容器制作成新的 docker镜像

```
docker commit -a 'Author' -m 'push is message' < containerId > < new-images-name >
docker push 
```

注意： 要注意`dockefile`所编排的，不要与修改的地方产生冲突