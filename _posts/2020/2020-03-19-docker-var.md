---
layout: post
title: "Docker 修改默认存储路径的一个方法"
keywords: ["docker"]
description: "Docker 修改默认存储路径的一个方法"
category: "docker"
tags: ["docker"]
---
## 背景
前期安装创建centOS的虚拟机时发现自己对linux的挂载点不清楚, 造成挂载点的分配不太均匀,如图:
root / 节点的大小设置的比较小 /home路径设置的一直比较大
但是docker 默认的存储路径在 /var/lib/docker下面 感觉不太好.

## 参考资料
[https://www.cnblogs.com/jinanxiaolaohu/p/8301786.html](https://www.cnblogs.com/jinanxiaolaohu/p/8301786.html)