---
layout: post
title: "mysql同时安装不同端口的数据库"
keywords: ["mysql"]
description: "mysql同时安装不同端口的数据库"
category: "mysql"
tags: ["mysql"]
---

### 背景
最近有一个数据库要迁移，因数据较大可能有些文件受了影响。于是换成这种方案来迁移下。

### 操作步骤
1.安装新的mysql,新端口为3307
```
/usr/bin/mysql_install_db --defaults-file=/etc/my_3307.cnf --datadir=/data/mysql --basedir=/usr --user=mysql
```
参考资料[https://blog.csdn.net/u012107049/article/details/72717030](https://blog.csdn.net/u012107049/article/details/72717030)

2.给予mysql权限
```
chown mysql:mysql /data/mysql/ -Rf
```

3.启动mysql
```
/bin/sh /usr/bin/mysqld_safe --defaults-extra-file=/etc/my_3307.cnf
```

### 检查是否成功
检查是否成功如有3307端口表示已成功了
```
netstat -tunlp | grep mysql

tcp        0      0 0.0.0.0:3306                0.0.0.0:*                   LISTEN      14249/mysqld_safe
tcp        0      0 0.0.0.0:3307                0.0.0.0:*                   LISTEN      14243/mysqld
```


### 另附上my_3307.cnf文件
```
[client]
port = 3307
socket = /data/mysql/mysql3307.sock
default-character-set = utf8mb4

[mysqld]
explicit_defaults_for_timestamp=true
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
user = mysql
port = 3307
bind-address = 0.0.0.0
socket = /data/mysql/mysql3307.sock
basedir=/usr
datadir=/data/mysql/
wait_timeout=31536000
interactive_timeout=31536000

[mysql]
default-character-set = utf8mb4
```

### 重要注意点
如果你上把线上的mysql数据目录变更时，且数据库有InnoDB数据表时，一定要把在运行中的mysql停掉。否则会一直迁移不成功。可能会出现以下错误
```
InnoDB: Error: tablespace id in file './yejr/yejr.ibd' is 15, but in the InnoDB
InnoDB: data dictionary it is 13.
InnoDB: Have you moved InnoDB .ibd files around without using the
InnoDB: commands DISCARD TABLESPACE and IMPORT TABLESPACE?
InnoDB: Please refer to
InnoDB: http://dev.mysql.com/doc/refman/5.0/en/innodb-troubleshooting.html
InnoDB: for how to resolve the issue.
InnoDB: cannot find or open in the database directory the .ibd file of
InnoDB: table `yejr/yejr`
InnoDB: in ALTER TABLE ... IMPORT TABLESPACE
```
参考资料[http://imysql.cn/2008_12_17_migrate_innodb_tablespace_smoothly](http://imysql.cn/2008_12_17_migrate_innodb_tablespace_smoothly)
