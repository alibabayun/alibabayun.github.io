---
layout: post
title: "防火墙未开启导致通过redis篡改.ssh/authorized_keys"
keywords: ["redis"]
description: "防火墙未开启导致通过redis篡改.ssh/authorized_keys"
category: "redis"
tags: ["redis"]
---

### 前言
今天突然发现无法免密码登陆服务器了，心里一凉，难道服务器被破坏了? 登陆上去查看 ~/.ssh/authorized_keys ，发现里面全是乱码，幸运的是有一个关键字 REDIS，难道是redis搞的？ 这么一想，我立马查看了一下防火墙规则，不知道谁把我之前配置好的规则清空了，现在默认接受所有端口连接，先把防火墙重新配置，阻止外部访问redis。 然后想办法恢复，我并不知道为什么redis会往authorized_keys写内容，于是只能google了一下。。

### redis-cli查询:
```
127.0.0.1:6379> config get dir
1) "dir"
2) "/root/.ssh"

127.0.0.1:6379> config get dbfilename
1) "dbfilename"
2) "authorized_keys"
```

### 原来是改了这个配置，于是手动恢复
```
127.0.0.1:6379> config set dir /var/lib/redis/6379
127.0.0.1:6379> config set dbfilename dump.rdb
```

* 参考1：https://blog.csdn.net/weixin_34358092/article/details/91759958
* 参考2： https://blog.csdn.net/qq_56426046/article/details/127077514
