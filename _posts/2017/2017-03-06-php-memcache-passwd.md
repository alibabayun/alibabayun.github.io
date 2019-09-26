---
layout: post
title: "memcached带密码连接"
keywords: ["memcached", "php"]
description: "memcached带密码连接"
category: "php"
tags: ["php", "memcached"]
---
{% include JB/setup %}

###### 背景
前段时间在作负载`弹性伸缩`时（不能确认新的服务器IP），
阿里云的mc连接可以免密码链接已加入白名单的服务器
但是他限制的是，必须自己手动加服务器到白名单，想要用其它阿里云服务器连接必须要带密码访问


##### 解决
于是就找到以下代码 
php版本要大于等于`5.6.25`

```
<?php
$memc = new Memcached();
$memc->setOption(Memcached::OPT_COMPRESSION, false);
$memc->setOption(Memcached::OPT_BINARY_PROTOCOL, true);
$memc->addServer("****.ocs.aliyuncs.com", 11211);
#$memc->setSaslAuthData("("****", "("****");
echo $memc->get("10");
?>
```