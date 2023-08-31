---
layout: post
title: "frp自定义域名穿透"
keywords: ["frp"]
description: "frp自定义域名穿透"
category: "frp"
tags: ["frp"]
---
## 前言
这个示例通过简单配置 HTTP 类型的代理让用户访问到内网的 Web 服务。
HTTP 类型的代理相比于 TCP 类型，不仅在服务端只需要监听一个额外的端口 vhost_http_port 用于接收 HTTP 请求，还额外提供了基于 HTTP 协议的诸多功能。

## 参考链接
[https://gofrp.org/docs/examples/vhost-http/](https://gofrp.org/docs/examples/vhost-http/)

## 配置文件
### 服务端
```
[common]
bind_port = 7000
#以下是http添加的重点
vhost_http_port = 7001
```

### 客户端
```
[common]
server_addr = 127.0.0.1
server_port = 7000

#[ssh]
#type = tcp
#local_ip = 192.168.1.1
#local_port = 22
#remote_port = 2222


[web]
type = http
local_ip = 192.168.1.1
local_port = 80
custom_domains = web.test.jd.com
host_header_rewrite = 192.168.1.1
```

最后访问：http://web.test.jd.com:7001/
