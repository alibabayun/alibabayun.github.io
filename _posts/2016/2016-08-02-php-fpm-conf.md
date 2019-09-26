---
layout: post
title: "php-fpm指定用户 启动端口"
keywords: ["php-fpm指定用户 启动端口"]
description: "php-fpm指定用户 启动端口"
category: "php-fpm"
tags: ["php-fpm"]
---
{% include JB/setup %}

找到 `php-fpm.conf` 文件 添加以下

```
[alibabayun]
user = alibabayun
group = alibabayun
listen = 127.0.0.1:9005
pm = dynamic
pm.max_children = 5
pm.start_servers = 2
pm.min_spare_servers = 1
pm.max_spare_servers = 3
```