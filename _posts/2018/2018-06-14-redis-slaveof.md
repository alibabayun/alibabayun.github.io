---
layout: post
title: "Redis Slaveof 命令主从切换"
keywords: ["redis"]
description: "Redis Slaveof 命令主从切换"
category: "redis"
tags: ["redis"]
---

Redis Slaveof 命令可以将当前服务器转变为指定服务器的从属服务器(slave server)。

如果当前服务器已经是某个主服务器(master server)的从属服务器，那么执行 SLAVEOF host port 将使当前服务器停止对旧主服务器的同步，丢弃旧数据集，转而开始对新主服务器进行同步。

另外，对一个从属服务器执行命令 SLAVEOF NO ONE 将使得这个从属服务器关闭复制功能，并从从属服务器转变回主服务器，原来同步所得的数据集不会被丢弃。

利用『 SLAVEOF NO ONE 不会丢弃同步所得数据集』这个特性，可以在主服务器失败的时候，将从属服务器用作新的主服务器，从而实现无间断运行。

##语法
redis Slaveof 命令基本语法如下：
```
redis 127.0.0.1:6379> SLAVEOF host port  
```

##可用版本
>= 1.0.0

##返回值
总是返回 OK 

##实例
```
redis 127.0.0.1:6379> SLAVEOF 127.0.0.1 6379
OK

redis 127.0.0.1:6379> SLAVEOF NO ONE
OK
```


参考链接[http://www.runoob.com/redis/server-slaveof.html](http://www.runoob.com/redis/server-slaveof.html)