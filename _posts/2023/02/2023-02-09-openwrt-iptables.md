---
layout: post
title: "ufi-openwrt开放端口"
keywords: ["linux"]
description: "ufi-openwrt开放端口"
category: "linx"
tags: ["linux"]
---

脚本如下
```
iptables -I INPUT 1 -p tcp --dport 80 -j ACCEPT
iptables -I INPUT 1 -p tcp --dport 22 -j ACCEPT
iptables -I INPUT 1 -p tcp --dport 554 -j ACCEPT
iptables -I INPUT 1 -p tcp --dport 8080 -j ACCEPT
/etc/init.d/firewall reload
```


启动时运行上面的脚本