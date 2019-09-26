---
layout: post
title: " 通过mysql show processlist 命令检查mysql锁的方法"
keywords: ["通过mysql"]
description: " 通过mysql show processlist 命令检查mysql锁的方法"
category: "通过mysql"
tags: ["通过mysql"]
---

`show processlist` 命令非常实用，有时候mysql经常跑到50%以上或更多，就需要用这个命令看哪个sql语句占用资源比较多，就知道哪个网站的程序问题了。


启动`mysql`，输入`show processlist`; 
如果有 SUPER 权限，则可以看到全部的线程，否则，只能看到自己发起的线程（这是指，当前对应的MySQL帐户运行的线程）。 
得到数据形式如下（只截取了三条）： 

```
mysql> show processlist; 
+-----+-------------+--------------------+-------+---------+-------+----------------------------------+---------- 
| Id | User | Host | db | Command | Time| State | Info 
+-----+-------------+--------------------+-------+---------+-------+----------------------------------+---------- 
|207|root |192.168.0.20:51718 |mytest | Sleep | 5 | | NULL 
|208|root |192.168.0.20:51719 |mytest | Sleep | 5 | | NULL 
|220|root |192.168.0.20:51731 |mytest |Query | 84 | Locked | 
select bookname,culture,value,type from book where id=001 
```


### 阿里云参考资料:

* [RDS for MySQL 只读实例同步延迟原因与处理](https://help.aliyun.com/knowledge_detail/41767.html)
* [RDS MySQL 表上 Metadata lock 的产生和处理](https://help.aliyun.com/knowledge_detail/41723.html)
* [RDS MySQL 管理长时间运行查询](https://help.aliyun.com/knowledge_detail/41735.html)
* [RDS MySQL CPU使用率高情况的原因和解决](https://help.aliyun.com/knowledge_detail/41715.html)
