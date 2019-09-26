---
layout: post
title: "Centos设置静态ip与DNS1"
keywords: ["Centos", "linux"]
description: "Centos设置静态ip与DNS1"
category: "linux"
tags: ["linux", "Centos"]
---
{% include JB/setup %}

1、如何绑定一个静态ip

```
vi /etc/sysconfig/network-scripts/ifcfg-eth0
```

2、设置ip

```
DEVICE=eth0
HWADDR=38:2C:4A:B0:19:B6
TYPE=Ethernet
UUID=e0458e52-86a2-4ecc-bcca-4e3fe0aec7c4
ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static

# ----- 192 start -------
#IPADDR=192.168.1.122
#NETMASK=255.255.255.0
#DNS1=119.29.29.29
#GATEWAY=192.168.1.1
# ----- 192 end -------


# ----- 172 start -------
IPADDR=172.16.1.122
NETMASK=255.255.0.0
DNS1=119.29.29.29
GATEWAY=172.16.0.1
# ----- 172 end -------
```