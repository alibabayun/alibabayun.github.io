---
layout: post
title: "linux 查看运行进程所在目录"
keywords: ["linux"]
description: "linux 查看运行进程所在目录"
category: "linux"
tags: ["linux"]
---

### 前言
通过ps及top命令查看进程信息时，只能查到相对路径，查不到的进程的详细信息，如绝对路径等。这时，我们需要通过以下的方法来查看进程的详细信息：
Linux在启动一个进程时，系统会在/proc下创建一个以PID命名的文件夹，在该文件夹下会有我们的进程的信息，其中包括一个名为exe的文件即记录了绝对路径，通过ll或ls –l命令即可查看。

### 在linux下查看进程用
```
ps -ef | grep XXX
```

### 查看pid所在的目录
```
ll /proc/PID
```

参考：https://blog.csdn.net/u014252478/article/details/87866066
