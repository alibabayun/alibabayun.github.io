---
layout: post
title: "linux查看进程的个数"
keywords: ["linux"]
description: "linux查看进程的个数"
category: "linux"
tags: ["linux"]
---
有时我们需要监控一个进程是是否存活，可以用以下命令解决

命令如下
```
ps -ef | grep java | grep -v grep | wc -l

```