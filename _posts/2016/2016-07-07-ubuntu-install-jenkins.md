---
layout: post
title: "ubuntu下安装Jenkins"
keywords: ["ubuntu下安装Jenkins"]
description: "ubuntu下安装Jenkins"
category: "ubuntu下安装Jenkins"
tags: ["Jenkins"]
---
{% include JB/setup %}

一、 在阿里云服务器安装时出现了以下错误

```
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libwagon-java all 1.0.0-2ubuntu2
  404  Not Found
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libmaven2-core-java all 2.2.1-8
  404  Not Found
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libmaven-plugin-testing-java all 1.2-3
  404  Not Found
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libmaven-common-artifact-filters-java all 1.2-1
  404  Not Found
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libmaven-dependency-tree-java all 1.2-4
  404  Not Found
Err http://mirrors.aliyuncs.com/ubuntu/ precise/universe libmaven-enforcer-plugin-java all 1.0-1

```

可能是阿里云`apt-get`镜像源不存在上面的包

二、 添加网易的国内镜像源

```
deb http://mirrors.163.com/ubuntu/ precise main restricted universe multiverse
deb http://mirrors.163.com/ubuntu/ precise-security main restricted universe multiverse
deb http://mirrors.163.com/ubuntu/ precise-updates main restricted universe multiverse
deb http://mirrors.163.com/ubuntu/ precise-proposed main restricted universe multiverse
deb http://mirrors.163.com/ubuntu/ precise-backports main restricted universe multiverse
deb-src http://mirrors.163.com/ubuntu/ precise main restricted universe multiverse
deb-src http://mirrors.163.com/ubuntu/ precise-security main restricted universe multiverse
deb-src http://mirrors.163.com/ubuntu/ precise-updates main restricted universe multiverse
deb-src http://mirrors.163.com/ubuntu/ precise-proposed main restricted universe multiverse
deb-src http://mirrors.163.com/ubuntu/ precise-backports main restricted universe multiverse
```

三、找到 `jenkins.war`文件 并执行以下命令

```
sudo java -jar /usr/share/jenkins/jenkins.war
```

至此完美运行了
![图]()