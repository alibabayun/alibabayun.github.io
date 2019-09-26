---
layout: post
title: "mysql导入txt"
keywords: ["mysql导入txt文件命令"]
description: "mysql导入txt文件命令"
category: "mysql"
tags: ["mysql", "命令"]
---
{% include JB/setup %}


### 导入 demo

```mysql
load data local infile 'd:/mysql_txt/wbh_zy.txt'
into table qr_number(qr_pk_number, qr_number); 
```

### mysql 随机得到数值

```mysql
update bs_vote set create_time=
create_time-((rand()*1702993)+1)
where id =1
;
SELECT FROM_UNIXTIME(create_time, '%Y-%m-%d %H:%i:%S') from bs_vote;
```