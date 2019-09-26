---
layout: post
title: "Centos7下安装新环境后不能联网，需要激活ONBOOT"
keywords: ["Linux"]
description: "Centos7下安装新环境后不能联网，需要激活ONBOOT"
category: "Linux"
tags: ["Linux"]
---


Centos7下安装新环境后不能联网？

1、找到网卡的配置文件,查看`ONBOOT`是否为`yes`如果为`no`将此项配置修改为yes

```
[alibabayun@32-XS-RD ~]$ vi /etc/sysconfig/network-scripts/ifcfg-enp2s0 

TYPE=Ethernet
BOOTPROTO=dhcp
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=enp2s0
UUID=2175d432-02af-42b1-82fc-ac1e74c14ef7
DEVICE=enp2s0
#ONBOOT=no
ONBOOT=yes

BOOTPROTO=static
# ----- 192 start -------
IPADDR=192.168.1.167
NETMASK=255.255.255.0
#DNS1=114.114.114.114
DNS1=119.29.29.29
GATEWAY=192.168.0.254
# ----- 192 end -------
```

2、重启网卡 `service network restart `激活成功

注意：如使用windows下虚拟机，可能要需要在 设置-网络-设置为桥接模式