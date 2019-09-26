---
layout: post
title: "Centos内存占满释放内存"
keywords: ["Centos", "linux"]
description: "Centos内存占满释放内存"
category: "linux"
tags: ["linux", "Centos"]
---
{% include JB/setup %}

今天用 `free -m` 查看，发现内存跑满了。
再 `top`，然后按下`shift+m`，也就是按内存占用百分比排序，发现排在第一的进程，才占用0.9%，那是什么占用的呢？谷歌了一下，据说是centos为了提高效率，把部分使用过的文件缓存到了内存里。如果是这样的话，我又不需要这样的文件性能，那就可以释放。如下两个命令就可以：

```
#sync
#echo 3 > /proc/sys/vm/drop_caches
```

内存释放后，就占用很低了，如下：
![img](https://img.alicdn.com/imgextra/i4/1819728314/TB2XJ_XeYJmpuFjSZFwXXaE4VXa_!!1819728314.jpg)