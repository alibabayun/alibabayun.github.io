---
layout: post
title: "centos7开机初始化"
keywords: ["linux"]
description: "centos7开机初始化"
category: "linux"
tags: ["linux"]
---

## 手工修改 `vim /etc/resolv.conf`

```
# 阿里dns
nameserver 223.5.5.5
nameserver 223.6.6.6
```

## 关闭防火墙

```
systemctl stop firewalld.service 
```
详情配置可参考 [https://www.cnblogs.com/faithH/p/11811286.html](https://www.cnblogs.com/faithH/p/11811286.html)

## 磁盘分区

详情配置可参考 [https://www.cnblogs.com/zishengY/p/7137671.html](https://www.cnblogs.com/zishengY/p/7137671.html)


## 安装宝塔

教程[https://www.bt.cn/bbs/thread-19376-1-1.html](https://www.bt.cn/bbs/thread-19376-1-1.html)

