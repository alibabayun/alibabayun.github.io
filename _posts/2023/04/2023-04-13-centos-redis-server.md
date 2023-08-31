---
layout: post
title: "Redis在Centos7上的安装部署[通俗易懂]"
keywords: ["redis"]
description: "Redis在Centos7上的安装部署[通俗易懂]"
category: "redis"
tags: ["redis"]
---

## 前言
Redis是一种高级key-value数据库。它跟memcached类似，不过数据可以持久化，而且支持的数据类型很丰富。有字符串，链表，集 合和有序集合。支持在服务器端计算集合的并，交和补集(difference)等，还支持多种排序功能。所以redis也可以被看成是一个数据结构服务器。

Redis的所有数据都是保存在内存中（效率高），然后不定期的通过异步方式保存到磁盘上(这称为“半持久化模式”)；也可以把每一次数据变化都写入到一个append only file(aof)里面(这称为“全持久化模式”)。

关于Redis更多的简介请参考Redis官方网站中文版，在这里我仅仅给出Redis在Centos7上的安装部署。


## 文档
文档 [https://cloud.tencent.com/developer/article/2052549](https://cloud.tencent.com/developer/article/2052549)

