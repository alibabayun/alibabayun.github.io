---
layout: post
title: "文件与目录的权限 - 修改 PHP-FPM 的用户"
keywords: ["centos", "php"]
description: "文件与目录的权限 - 修改 PHP-FPM 的用户"
category: "php"
tags: ["php", "centos"]
---
{% include JB/setup %}

最近在安装本地环境时，出现一个奇葩的问题
配置好	`nginx` 后，在`/data/`下的php文件可以访问，但是 将代码放到 `/home/`  下时，就访问不了
查了上午，定位是 `PHP-FPM` 的用户配置问题，默认会配置`apache`用户，它只对`data`目录下有效
所以我们要找到php-fpm.conf下，变更他的用户信息

详情参考
[https://ninghao.net/video/1605](https://ninghao.net/video/1605)