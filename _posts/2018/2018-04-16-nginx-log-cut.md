---
layout: post
title: "nginx日志切分脚本"
keywords: ["nginx"]
description: "nginx日志切分脚本"
category: "nginx"
tags: ["nginx"]
---
{% include JB/setup %}

```
#!/bin/bash
#nginx日志切分脚本
#
#nginx 日志存放路径
logs_path='/var/logs/'

mkdir -p ${logs_path}$(date -d "yesterday" + "%Y")/(date -d "yesterday" + "%m")/
mv ${logs_path}access.log ${logs_path}$(date -d "yesterday" + "%Y")/$(date -d "yesterday" + "%m")/access_$(date -d "yesterday" + "%Y%m%d").log
kill -USR1 `cat /usr/local/nginx/nginx.pid`
```