---
layout: post
title: "docker导出导入全部镜像"
keywords: ["docker"]
description: "docker导出导入全部镜像"
category: "docker"
tags: ["docker"]
---

## 背景
由于工作需要，要一次性导出机器上全部的镜像。

## 命令
>  导出命令：
```
docker save $(docker images --format '{{.Repository}}:{{.Tag}}') -o allinone.tar
```

>  导入命令：
```
docker load -i allinone.tar
```