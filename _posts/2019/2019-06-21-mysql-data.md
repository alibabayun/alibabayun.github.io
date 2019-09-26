---
layout: post
title: "Mysql数据库损坏，通过idb和frm恢复表结构和数据"
keywords: ["mysql"]
description: "Mysql数据库损坏，通过idb和frm恢复表结构和数据"
category: "mysql"
tags: ["mysql"]
---

### 前言

呃呃呃，由于前几天作死，作死内容就不解释了…怪尴尬的… 也不希望各位模仿
作死结果是 数据库彻底完犊子了,相关项目运行跟着完犊子了。

值得庆幸的是，数据根据网上经验找回来99%算是比较幸运的了,根据网上经验总结一下“挽救措施”。



### .frm恢复表结构

`温馨提示:如果你已经恢复了表数据，可以直接忽略此操作，开始下一步操作`

MySql创建每张表后都会在“mysql安装目录/data/数据库名/”目录下创建一个“表名.frm”文件。

该.frm文件并不能直接打开，但是它可以帮助你恢复你的表结构~~

具体操作如下：

1、准备恢复bhusk数据库中的表结构，其中bhusk数据库中包含了admin表。

2、我们首先连接数据库，创建数据库bhusk,自己可视化工具或许会快一点
```
create database bhusk; 
```

3、然后在该数据库下创建表admin ，只需要包含一个简单的字段便可，主要是创建出一个同名的表，方便接下来的替换。
```
create table `admin`( id varchar(32) not null);
```

4、这一步请将mysql数据库服务关闭

5、将之前旧的数据库留下来的/data/bhusk/admin.frm文件覆盖掉现在新的数据库/data/bhusk目录下的admin.frm文件。

6、给这个admin.frm加权限，所有者mysql ，权限660（你可以参考别的ibd文件所有者和权限设置） （这一步几乎可以忽略）此步为linux权限操作

7、最后启动数据库，可以查表结构了


### .idb恢复表数据

1、 现在admin表结构有了，开始准备恢复数据，如果通过以上方法恢复表结构，那一定在`/data/bhusk/`目录下会有`admin.idb`文件。

2、接下来执行`alter table `admin` discard tablespace;`执行完之后，数据库目录下的user.ibd文件就没了

3、把你备份的ibd放到消失的admin.ibd文件那里。

4、给这个文件加权限，所有者mysql ，权限660（你可以参考别的ibd文件所有者和权限设置）（这一步几乎可以忽略）此步为linux权限操作

5、执行 `alter table admin import tablespace;` 执行完，表数据就可读了，这时候会丢失一些诸如表行记录数等存在系统表里的信息，不过那些信息无所谓。表其实这时候已经可以正常用了。如果不放心，可以导出sql语句或txt，再创建表导入。

### 总结

数据无价，一定要细心。


### 来源:
[https://mp.weixin.qq.com/s/z57m1Cao64fwjT58KYrhPw](https://mp.weixin.qq.com/s/z57m1Cao64fwjT58KYrhPw)