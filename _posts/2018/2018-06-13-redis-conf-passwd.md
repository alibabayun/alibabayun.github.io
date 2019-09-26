---
layout: post
title: "如何给redis设置密码"
keywords: ["redis"]
description: "如何给redis设置密码"
category: "redis"
tags: ["redis"]
---
redis没有实现访问控制这个功能，但是它提供了一个轻量级的认证方式，可以编辑redis.conf配置来启用认证。

> 1、初始化Redis密码：

在配置文件中有个参数： `requirepass`  这个就是配置redis访问密码的参数；
* 比如 `requirepass test123`；
*（Ps:需重启Redis才能生效）
* redis的查询速度是非常快的，外部用户一秒内可以尝试多大150K个密码；所以密码要尽量长（对于DBA 没有必要必须记住密码）；


> 2、不重启Redis设置密码：
* 在配置文件中配置requirepass的密码（当redis重启时密码依然有效）。
``` 
redis 127.0.0.1:6379> config set requirepass test123
```
* 查询密码：
```
redis 127.0.0.1:6379> config get requirepass
   (error) ERR operation not permitted
```
* 密码验证：
```
redis 127.0.0.1:6379> auth test123
   OK
```
* 再次查询：
```
redis 127.0.0.1:6379> config get requirepass
   1) "requirepass"
   2) "test123"
```
PS：如果配置文件中没添加密码 那么redis重启后，密码失效；

>  3、登陆有密码的Redis：
* 在登录的时候的时候输入密码：
```
 redis-cli -p 6379 -a test123
```
* 先登陆后验证：
```
redis-cli -p 6379
redis 127.0.0.1:6379> auth test123
OK
```
*  AUTH命令跟其他redis命令一样，是没有加密的；阻止不了攻击者在网络上窃取你的密码；
*  认证层的目标是提供多一层的保护。如果防火墙或者用来保护redis的系统防御外部攻击失败的话，外部用户如果没有通过密码认证还是无法访问redis的。
