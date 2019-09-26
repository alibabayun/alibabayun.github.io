---
layout: post
title: "mysql中使用if查询"
keywords: ["mysql中使用if查询"]
description: "mysql中使用if查询"
category: "mysql"
tags: ["mysql"]
---
{% include JB/setup %}

题目
![img](https://img.alicdn.com/imgextra/i3/1819728314/TB2BqfMXVHzQeBjSZFuXXanUpXa_!!1819728314.png)


查询语句

````
select 
	te as 老师号,
	sum(if(weed=1,1,0)) as '星期一',
	sum(if(weed=2,1,0)) as '星期二',
	sum(if(weed=3,1,0)) as '星期三',
	sum(if(weed=4,1,0)) as '星期四',
	sum(if(weed=4,1,0)) as '星期五',
	sum(if(weed=4,1,0)) as '星期六',
	sum(if(weed=4,1,0)) as '星期天'
from
	test
 group by 
 	te
```

数据表

```
CREATE TABLE `test` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`te` INT(11) NULL DEFAULT NULL,
	`weed` INT(11) NULL DEFAULT NULL,
	`is_show` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_german2_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;
````