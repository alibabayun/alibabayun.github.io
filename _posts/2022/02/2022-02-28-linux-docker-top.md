---
layout: post
title: "宿主机 根据 PID 获取容器所在的 Pod 名称"
keywords: ["linux"]
description: "linux "
category: "linux"
tags: ["linux"]
---

### 直接看进程
```
ps -aux | grep 26490
```

### Container ID

```
$ cat /proc/14338/cgroup
````

### 参考资料
https://cloud.tencent.com/developer/article/1664123