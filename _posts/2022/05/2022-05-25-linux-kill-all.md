---
layout: post
title: "linux 查看并杀死僵尸进程"
keywords: ["linux"]
description: "linux 查看并杀死僵尸进程"
category: "linux"
tags: ["linux"]
---


命令如下
```
ps -A -o stat,ppid,pid,cmd | grep -e '^[Zz]' | awk '{print $2}' | xargs kill -9
```


参考链接
https://developer.aliyun.com/article/555362