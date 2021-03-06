---
layout: post
title: "Linux下使用iptables禁止指定IP地址的网络连接"
keywords: ["iptables"]
description: "Linux下使用iptables禁止指定IP地址的网络连接"
category: "iptables"
tags: ["iptables"]
---

在Linux下，使用iptables来维护IP规则表。要封停或者是解封IP，其实就是在IP规则表添加规则。
要禁止指定IP地址的网络连接，可以使用以下两种方法来快速实现。

## 1.禁止特定IP的连接
要禁止一个IP，使用下面这条命令：
```
iptables -I INPUT -s ***.***.***.*** -j DROP
```
要解封一个IP，使用下面这条命令：
```
iptables -D INPUT -s ***.***.***.*** -j DROP
```
参数-I是表示 Insert （添加），-D表示 Delete （删除）。后面跟的是规则， INPUT 表示入站，***.***.***.*** 表示要封停的IP， DROP 表示放弃连接。

可以使用下面的命令来查看当前的IP规则表： 
```
iptables -list
```
```
[sudo] password for wsliu: 
Chain INPUT (policy ACCEPT)
target     prot opt source               destination         
DROP       all  --  120.203.229.36       anywhere            
DROP       all  --  117.169.67.5         anywhere            
DROP       all  --  103.52.217.131       anywhere            

Chain FORWARD (policy ACCEPT)
target     prot opt source               destination         

Chain OUTPUT (policy ACCEPT)
target     prot opt source               destination
--------------------- 


## 2. 禁止IP段的网络连接
添加IP段到封停列表中：

```
iptables -I INPUT -s 121.0.0.0/8 -j DROP
```

转自[https://blog.csdn.net/tobacco5648/article/details/51506508](https://blog.csdn.net/tobacco5648/article/details/51506508)