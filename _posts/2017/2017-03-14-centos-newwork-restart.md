---
layout: post
title: "centos首次使用时要激活网卡"
keywords: ["centos"]
description: "centos首次使用时要激活网卡"
category: "centos"
tags: [ "centos"]
---
{% include JB/setup %}

centos刚安装好的新环境时，要激活网卡方可进行上网
找到这个文件

```
vim /etc/sysconfig/network-scripts/ifcfg-enp2s0
```

将里面的onboot参数设置为yes

```
ONBOOT=yes
```

重启网卡
service network restart