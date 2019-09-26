---
layout: post
title: " 如何在mysql数据库中保存emoji特殊字符"
keywords: [" 如何在mysql数据库中保存emoji特殊字符"]
description: " 如何在mysql数据库中保存emoji特殊字符"
category: "mysql"
tags: ["mysql"]
---

MySQL在5.5.3版本之后增加了这个utf8mb4的编码，mb4就是most bytes 4的意思，专门用来兼容四字节的unicode。其实，utf8mb4是utf8的超集，理论上原来使用utf8，然后将字符集修改为utf8mb4，也 会不会对已有的utf8编码读取产生任何问题。
一次在做微信二开的项目时，发现保存微信用户信息的nickname时报错，寻找原因发现是有些用户昵称是带有emoji表情符的，并且当时的数据库表属性设置的字符编码还是utf8的，数据库当然会报错。经过网上搜索一些资料，找到了完整的解决方案：

* 修改database,table,column字符集：

```
[sql] view plain copy print?
ALTER DATABASE database_name CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;  
ALTER TABLE table_name CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;  
ALTER TABLE table_name CHANGE column_name VARCHAR(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;      
```

* 编辑my.cnf文件，在对应章节添加如下内容：

```
[sql] view plain copy print?
[client]  
default-character-set = utf8mb4  
[mysql]  
default-character-set = utf8mb4  
[mysqld]  
character-set-client-handshake = FALSE  
character-set-server = utf8mb4  
collation-server = utf8mb4_unicode_ci  
init_connect='SET NAMES utf8mb4'  
```

* 重启mysql服务即可


aliyun-144配置如下

```
[client]
default-character-set=utf8mb4

[mysqld]
character_set_server=utf8mb4
character_set_client=utf8mb4
collation-server=utf8mb4_unicode_ci
init_connect='SET NAMES utf8mb4'
max_connections=1000
#skip-grant-tables
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES 

[mysql]
default-character-set = utf8mb4
#socket = /var/lib/mysql/mysql.sock
```