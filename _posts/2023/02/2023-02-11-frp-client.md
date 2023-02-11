---
layout: post
title: "frp客户端多个端口转发"
keywords: ["frp"]
description: "frp客户端多个端口转发"
category: "frp"
tags: ["frp"]
---

记录下配置文件
```
[common]
server_addr = 120.48.114.115
server_port = 7000

[ssh7055]
type = tcp
local_ip = 127.0.0.1
local_port = 22
remote_port = 7055

[web7055]
type = tcp
local_ip = 192.168.1.11
local_port = 80
remote_port = 7056
custom_domains = 120.48.114.115
```
