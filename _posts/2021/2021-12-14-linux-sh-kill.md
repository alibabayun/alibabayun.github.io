---
layout: post
title: "grep 找出进程pid并kill"
keywords: ["linux"]
description: "linux "
category: "linux"
tags: ["linux"]
---

最近个人服务器出现了一个kjj病毒进程，还没找到源，先手动定时把这个进程kill掉
记录下脚本，方便后面使用


```
/usr/bin/ps -ef | grep "kjj" | grep -v grep | awk '{print $2}' | xargs kill -9
```