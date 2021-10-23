---
layout: post
title: "oracle数据库常用"
keywords: ["composer"]
description: "oracle数据库常用"
category: "oracle"
tags: ["oracle"]
---

## 参考资料：
- https://www.cnblogs.com/mmzs/p/11210625.html
- https://blog.csdn.net/lzqinfen/article/details/9001550

## 常用命令
1.导出数据库备份
```
exp           用户名/密码@ip:port/instance_name file=导出路径 full=y
linux:  su - oracle
例子：   exp  root/123456@127.0.0.1:1521/orcl file=/tmp/dc.dmp log=/tmp/dump.log    
doller符

window
exp  username/password@xx.xx.xx.xx:1521/orcl file=D:\bak\hw_monster.dmp
```

2.导入数据库备份
```
导入(windows cmd命令)：
       imp 用户名/密码@ip:port/instance_name file=导出文件所在路径 full=y
例子： imp root/123456@127.0.0.1:1521/orcl file=d:\dc.dmp full=yowner=ckms_cm
```

3.创建用户  给予表权限
```
创建一个新用户：create user abc identified by 123456;
授予DBA权限： grant connect,resource,dba to abc;
```

4.查询、修改oracle并发数设置
```
select count(*) from v$process; --当前的连接数
select value from v$parameter where name = 'processes' --数据库允许的最大连接数
alter system set processes = 300 scope = spfile;   修改最大连接数:
```

5.重启数据库
```
shutdown immediate；
startup；
select value from v$parameter where name = 'processes';
select name from v$datafile;
```

6.查看状态
```
lsnrctl status
```

7.设备数据库
```
set ORACLE_SID=orcl;
```

8.登陆数据库
```
sqlplus username/password@orcl   as sysdba
```

9.关闭数据库
```
shutdown immediate
shutdown abort
```

10.启动数据库
```
startup
```

11.查看日志存放目录
```
show parameter dump_dest;
```

##常见问题
* control01.ctl 文件不见了
参考资料 https://www.cnblogs.com/djlsunshine/p/14516621.html

