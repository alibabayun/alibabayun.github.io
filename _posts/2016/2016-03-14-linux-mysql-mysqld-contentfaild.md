---
layout: post
title: "mysql不能使用客户端ip登陆"
keywords: ["mysql 10060远程不能访问", "mysql不能使用客户端登陆", "mysql使用客户端ip登陆"]
description: "如何用ip登陆mysql"
category: "技术分享"
tags: ["分享", "走过的坑"]
---
{% include JB/setup %}

### 背景
搭一个线上的服务器环境，想使用本地的mysql管理工具去连接

### 出现的问题与解决方法
在这过程中把所遇到难点先总结下

* 使用root账户登陆时，密码必须

* 使用ip在管理数据库地址连接时要设置权限

```
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
```

* 出现的报错问题**MySQL错误：Can't connect to(10060)**

```
变更 my.conf里面的mysqld 连接端口 3306换成其它的
```