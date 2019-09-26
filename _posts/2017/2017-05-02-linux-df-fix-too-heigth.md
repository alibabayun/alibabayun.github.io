---
layout: post
title: " df 和 du 查看磁盘空间大小不一致"
keywords: ["linux"]
description: " df 和 du 查看磁盘空间大小不一致"
category: "linux"
tags: ["linux"]
---

参考资料
[https://help.aliyun.com/knowledge_detail/42537.html](https://help.aliyun.com/knowledge_detail/42537.html)

清除内存脚本：
```
#! /bin/bash
lsof |grep deleted |awk  '{print "kill -9 " $2}' |sh
killall /usr/local/bin/php-fpm
/etc/init.d/php-fpm start
killall /usr/local/bin/nginx
/usr/local/bin/nginx
```