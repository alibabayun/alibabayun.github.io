---
layout: post
title: "mysql得到排名"
keywords: ["mysql得到排名"]
description: "mysql得到排名"
category: "mysql"
tags: ["mysql"]
---
{% include JB/setup %}

###  关于mysql的“行号”

在数据库操作中，有时会遇到一些相邻数据的操作，这种相邻条件并不一定是以主键相邻的数据，如任意条件排名/随机记录（不能重复）
 
一般这种操作立刻就想到表行号（指针/游标），但是mysql并没有实现这样的机制，
 
一般我们可以通过用临时变量、count、临时表等根据应用不同生成mysql行号

测试表数据如下：

```
mysql> SELECT * FROM test_score;  
+----+-------+--------+  
| id | score | name   |  
+----+-------+--------+  
|  1 |   100 | 张三 |  
|  3 |    80 | 李四 |  
|  5 |   101 | 王五 |  
|  6 |    37 | 赵六 |  
|  7 |    45 | 钱七 |  
+----+-------+--------+ 
```

 
如下例子的排名问题
 
我们想按照积分对他们排名，我们可以使用临时变量来得到行记录

```
##如果是有limit n, m 的话，这里rowNo应该是 n * m  
mysql> set @rowNo = 0;  
mysql> SELECT *, (@rowNo := @rowNo + 1) as ranking FROM test_score ORDER BY score DESC;  
+----+-------+--------+---------+  
| id | score | name   | ranking |  
+----+-------+--------+---------+  
|  5 |   101 | 王五 |       1 |  
|  1 |   100 | 张三 |       2 |  
|  3 |    80 | 李四 |       3 |  
|  7 |    45 | 钱七 |       4 |  
|  6 |    37 | 赵六 |       5 |  
+----+-------+--------+---------+  
## 使用完毕记得重置这个变量  
mysql> set @rowNo = 0;  
```

如果我们想只找钱七的排名的话怎么办？

```
mysql> SELECT COUNT(id) + 1 AS ranking FROM test_score WHERE score > (SELECT score FROM test_score WHERE `name` = '钱七');   
+---------+  
| ranking |  
+---------+  
|       4 |  
+---------+  
```

```
SELECT
	*
FROM
	(
		SELECT
			id,
			babyname,
			phone,
			vote,
      address,
      `status`,
			(@rowno :=@rowno + 1) AS rowno
		FROM
			bs_baby,
			(SELECT(@rowno := 0)) b WHERE `status`=1
		ORDER BY
			vote DESC LIMIT 1000
	) a
ORDER BY
	rowno
```


