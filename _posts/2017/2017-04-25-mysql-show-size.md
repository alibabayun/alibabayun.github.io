---
layout: post
title: "mysql查看里面所有库的大小"
keywords: ["mysql"]
description: "mysql查看里面所有库的大小"
category: "mysql"
tags: ["mysql"]
---
{% include JB/setup %}

```
SELECT TABLE_SCHEMA,
       (sum(data_length)/1024/1024) AS data_size,
       (sum(index_length)/1024/1024) AS index_size
  FROM information_schema.tables
 GROUP BY TABLE_SCHEMA
 ORDER BY data_size desc;
```