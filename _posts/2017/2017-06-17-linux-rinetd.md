---
layout: post
title: "Linux端口映射转发工具rinetd安装使用"
keywords: ["Linux"]
description: "Linux端口映射转发工具rinetd安装使用"
category: "Linux"
tags: ["Linux"]
---

简介
Rinetd是为在一个Unix和Linux操作系统中为重定向传输控制协议(TCP)连接的一个工具。Rinetd是单一过程的服务器，它处理任何数量的连接到在配置文件etc/rinetd中指定的地址/端口对。尽管rinetd使用非闭锁I/O运行作为一个单一过程，它可能重定向很多连接而不对这台机器增加额外的负担。

1、下载wget http://www.boutell.com/rinetd/http/rinetd.tar.gz
    版本号`rinetd 0.62`

2、安装
```
    tar zxvf rinetd.tar.gz
    cd rinetd
```

3、运行make  #可能会出现错误，需如下修改：
```
    vi rinetd.c
    输入/bindPort >= 65536 查询
    输入/connectPort >= 65536查询

    将查询到的65536修改为65535  不然在make的时候会提示超出系统最大定义端口
```

4、手动建目录`/usr/man/man8`

5、make install
    成功后会提示文件路径install -m 700 rinetd /usr/sbin，即程序放在此位置

6、建立配置文件
    `/etc/rinetd.conf`
    内容格式：源IP 源端口 要跳转的IP 要跳转的端口

7、启动程序：`rinetd`

8、查看是否启动成功：`netstat -tanulp|grep rinetd`

9、加入开机启动：
```
    vi /etc/rc.d/rc.local
    加入/usr/sbin/rinetd
```

10、其他注意：
    不支持FTP的跳转
    要停掉程序：pkill rinetd

------------------------另一篇------------------------

安装
#### 直接上脚本

```
vim install_rinetd.sh

#!/bin/bash 
wget http://www.boutell.com/rinetd/http/rinetd.tar.gz
tar zxvf rinetd.tar.gz
cd rinetd
mkdir -p /usr/man/man8
make && make install
```

####  执行脚本进行安装： 
```
sudo sh install_rinetd.sh
```

####  配置文件
```
编译配置文件： vim /etc/rinetd.conf
```

####  配置文件格式很简单：
端口转发： [Source Address] [Source Port] [Destination Address] [Destination Port]

端口映射： bindaddress bindport connectaddress connectport

#### 启动程序
```
/usr/sbin/rinetd -c /etc/rinetd.conf
```

#### 查看是否启动成功
```
netstat -tanulp|grep rinetd
```

#### 加入开机启动
```
vi /etc/rc.d/rc.local
加入/usr/sbin/rinetd -c /etc/rinetd.conf
```

#### 注意事项
1. rinetd.conf中绑定的本机端口必须没有被其它程序占用
2. 运行rinetd的系统防火墙应该打开绑定的本机端口