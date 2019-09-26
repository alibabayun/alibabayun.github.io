---
layout: post
title: "mysqldump 只需要导出一个表的部分数据，如何写？"
keywords: ["mysqldump 只需要导出一个表的部分数据，如何写？"]
description: "mysqldump 只需要导出一个表的部分数据，如何写？"
category: "mysqldump"
tags: ["mysqldump"]
---
{% include JB/setup %}

mysqldump 导出某表的部分数据 

```
mysqldump -uroot -proot -hlocalhost db_name table_name --where=" id < 10" > test.sql 
```
相当于 select * from table_name where id < 10 的数据以及表结构都被导入拉test.sql中！