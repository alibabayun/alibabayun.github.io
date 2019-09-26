---
layout: post
title: "linux设置iptables"
keywords: ["linux"]
description: "linux设置iptables"
category: "linux"
tags: ["linux"]
---
{% include JB/setup %}


```
#!/bin/bash
iptables -F
ip=`ifconfig eth0 | grep "inet addr" | cut -f 2 -d ":" | cut -f 1 -d " "`
iptables -A INPUT -p tcp -s $ip --dport 11211 -j ACCEPT
iptables -A INPUT -p tcp -s 10.*.147.42 --dport 11211 -j ACCEPT
iptables -A INPUT -p tcp -s 10.*.93.8 --dport 11211 -j ACCEPT
iptables -A INPUT -p tcp -s 10.*.150.10 --dport 11211 -j ACCEPT
iptables -A INPUT -p tcp -s 127.0.0.1 --dport 11211 -j ACCEPT
iptables -A INPUT -p tcp -s 127.0.0.1 --dport 3306 -j ACCEPT
iptables -A INPUT -p tcp -s 0.0.0.0   --dport 3306 -j ACCEPT
iptables -A INPUT -p tcp -m tcp --dport 11211 -j DROP
service iptables save
service iptables restart
```