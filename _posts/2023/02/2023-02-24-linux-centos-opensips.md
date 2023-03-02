---
layout: post
title: "opensips服务搭建"
keywords: ["mqtt"]
description: "opensips服务搭建"
category: "mqtt"
tags: ["mqtt"]
---

## 前言
OpenSIPS是目前世界上主流的两个SIP软交换引擎(其中另外一个是kamailio)或者SIP信令服务器（个人认为是比较正确的称谓）

## 官网参考资料
[https://www.opensips.org/Documentation/Manual-3-3](https://www.opensips.org/Documentation/Manual-3-3)


## 安装
[https://yum.opensips.org/packages.php?v=3.3](https://yum.opensips.org/packages.php?v=3.3)
```
yum remove -y opensips*
rpm -i opensips-yum-releases-3.3-6.el7.noarch.rpm
yum install -y opensips
yum install -y opensips-cli
```

## opensips-cli配置文件
[https://www.opensips.org/Documentation/Install-DBDeployment-3-3](https://www.opensips.org/Documentation/Install-DBDeployment-3-3)

## 数据库配置
[https://github.com/OpenSIPS/opensips-cli/blob/master/docs/modules/database.md#configuration](https://github.com/OpenSIPS/opensips-cli/blob/master/docs/modules/database.md#configuration)

添加配置 `vi ~/.opensips-cli.cfg`
```
[default]
#database_modules: acc clusterer dialog dialplan dispatcher domain rtpproxy usrloc
database_modules: ALL

#database_admin_url: postgres://root@localhost
database_admin_url: mysql://root@localhost
```

然后执行
```
opensips-cli -x database create
```
