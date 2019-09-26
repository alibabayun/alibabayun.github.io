---
layout: post
title: "mysql 查看当前ip连接数"
keywords: ["mysql 查看当前ip连接数"]
description: "mysql 查看当前ip连接数"
category: "mysql"
tags: ["mysql"]
---
{% include JB/setup %} 


mysql 查看当前ip连接数

```
mysql -u root -h121.40.116.5 -p123456 -e"show processlist\G;"| egrep "Host\:" | awk -F: '{ print $2 }'| sort | uniq -c 
```