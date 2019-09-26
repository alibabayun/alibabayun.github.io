---
layout: post
title: "centos开发机网卡设置"
keywords: ["centos", "network"]
description: "centos开发机网卡设置"
category: "network"
tags: ["network", "centos"]
---
{% include JB/setup %}

```
DEVICE=eth0
HWADDR=38:2C:4A:B0:19:B6
TYPE=Ethernet
UUID=e0458e52-86a2-4ecc-bcca-4e3fe0aec7c4
ONBOOT=yes
NM_CONTROLLED=yes
BOOTPROTO=static

# ----- 192 start -------
IPADDR=192.168.1.122
NETMASK=255.255.255.0
DNS1=119.29.29.29
GATEWAY=192.168.1.1
# ----- 192 end -------


# ----- 172 start -------
#IPADDR=172.16.1.122
#NETMASK=255.255.0.0
#DNS1=119.29.29.29
#GATEWAY=172.16.0.1
# ----- 172 end -------

```
