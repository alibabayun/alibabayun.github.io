---
layout: post
title: "一条命令深度清理你的mac"
keywords: ["mac"]
description: "一条命令深度清理你的mac"
category: "mac"
tags: ["mac"]
---

## 前言
mac 用了一段时间后很快发现硬盘空间不够了,就想找一些磁盘清理的工具,但是发现居然都是收费的.
就手工操作吧.方法其实非常简单.
就一条命令,


## 解决方案
如下
```
cd /
du -hd 5 |grep -n '\dG' |sort
```

他会列出所有目录超过1G的文件夹,然后你逐个分析就可以了. 看看哪些文件夹不应该那么大
参考 ：https://www.cnblogs.com/baizx/p/9194931.html