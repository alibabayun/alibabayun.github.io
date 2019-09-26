---
layout: post
title: "Docker容器启动后自动退出、或进入“restarting”状态"
keywords: ["Docker容器启动后自动退出、或进入“restarting”状态"]
description: "Docker容器启动后自动退出、或进入“restarting”状态"
category: "Docker"
tags: ["Docker"]
---
{% include JB/setup %}

容器启动后自动退出、或进入“restarting”状态

对于初学者而言，一个经常遇到的问题是Docker容器启动后自动退出，docker logs也没有特殊输出。如果容器启动参数时包含“--restart=always” 或“--restart”则会不停重启

由于Ubuntu镜像缺省命令是`/bin/bash` 它在执行后会自动退出，我们需要将在服务的`“command”`在“更多设置”中将其修改为`/bin/sh -c "while true; do sleep 10; done" `这样的死循环；这样可以保证容器的持续运行

建议：用户在Docker镜像的`Dockerfile`中应该设置正确的CMD或Entrypoint参数，来保证容器的正确执行

更多问题请查看:

https://yq.aliyun.com/articles/59144