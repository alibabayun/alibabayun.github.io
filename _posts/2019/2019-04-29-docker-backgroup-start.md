---
layout: post
title: "docker容器中启动服务"
keywords: ["docker"]
description: "docker容器中启动服务"
category: "docker"
tags: ["docker"]
---

记一下docker中的一些问题

```
docker run -ditP --privileged e79bb2216fe7  /usr/sbin/init
```

### 1.centos7操作SSH/SSHD服务(查看/启动/重启/自启)
查看状态：
```
systemctl status sshd.service
```
启动服务：
```
systemctl start sshd.service
```
重启服务：
```
systemctl restart sshd.service
```
开机自启：
```
systemctl enable sshd.service
```

### 2.Centos7 Docker容器中报错 `Failed to get D-Bus connection: Operation not permitted`

在运行的docker容器中 执行命令启动nginx
```
[root@node132 ~]# docker run -it nginx-1 /bin/bash    
[root@03e74fb601c1 /]# systemctl start nginx    
Failed to get D-Bus connection: Operation not permitted
```

如果要是用systemctl 管理服务就要加上参数 `--privileged `来增加权，并且不能使用默认的`bash`，换成 `init`，命令如下
```
[root@node132 ~]# docker run -d -it --privileged nginx-1 /usr/sbin/init
362306ddc6f03919fcf22854da065d46c5231e8c140e636656c1bbbe497f9b62
[root@node132 ~]# docker ps 
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
362306ddc6f0        nginx-1             "/usr/sbin/init"    2 seconds ago       Up 1 second      
```

### 3.docker 启动mysql时自动执行脚本
参考资料
[https://blog.csdn.net/qq_32923745/article/details/80817477](https://blog.csdn.net/qq_32923745/article/details/80817477)

### 3.docker 启动端口自动分配映射
在docker run时加上`-P`如
```
docker run -ditP  --privileged  XXX/centos7:v9.3.9  /usr/sbin/init
```

### 7.Centos 7 如何卸载docker
1、首先搜索已经安装的docker 安装包 
```
yum list installed|grep docker 
```
2、 分别删除安装包 
```
yum –y remove docker.x86_64 
```
3、 删除docker 镜像 
```
rm -rf /var/lib/docker 
```

参考资料[https://www.jianshu.com/p/438f5fdc696b](https://www.jianshu.com/p/438f5fdc696b)
参考资料[https://www.runoob.com/docker/centos-docker-install.html](https://www.runoob.com/docker/centos-docker-install.html)

### 8.Dockerfile中如何自动回答标准输入的问题
https://blog.csdn.net/leon_wzm/article/details/78260795