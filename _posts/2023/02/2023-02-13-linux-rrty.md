---
layout: post
title: "rtty反向代理 WebTTYLinux 远程维护调试工具"
keywords: ["linux"]
description: "rtty反向代理 WebTTYLinux 远程维护调试工具"
category: "linux"
tags: ["linux"]
---

## 前言
rtty 是一个反向代理 WebTTY。它由客户端和服务端组成。服务端采用 Go 语言实现， 以及使用了 vue + iview。你可以基于你设置的设备 ID（不设置则为设备的 MAC 地址）通过 Web 浏览器访问你的任意一台终端。

rtty 非常适合远程维护你的或者你公司的部署在全球各地的成千上万的 Linux 设备。
![](https://img.linux.net.cn/data/attachment/album/201803/22/110828o8qz8h17q7sgg09q.jpg)

## 部署参考 文档
[https://linux.cn/article-9472-1.html](https://linux.cn/article-9472-1.html)

## 服务端配置
cat rttys.conf
```
addr-dev: :5915
addr-user: :5916

# Automatically select an available port in default
addr-http-proxy: :5917

#http-proxy-redir-url:

#ssl-cacert: /etc/rttys/rttys.ca
#ssl-cert: /etc/rttys/rttys.crt
#ssl-key: /etc/rttys/rttys.key

#token: a1d4cdb1a3cd6a0e94aa3599afcddcf5

# No login required to connect device.
# Values can be device IDs separated by spaces,
# or a "*" indicates that all devices do not require login
# http://localhost:5913/connect/rtty1
#white-list: "*"
#white-list: rtty1 rtty2

# database source
#db: sqlite://rttys.db
db: mysql://root:user@passwd@tcp(localhost)/rttys4
#db: mysql://rttys:rttys@tcp(localhost)/rttys
```

## 服务端 rttys.service
```
[Unit]
Description=rttys
After=network.target

[Service]
ExecStart=/usr/local/bin/rttys run -c /etc/rttys/rttys.conf
TimeoutStopSec=5s

[Install]
WantedBy=multi-user.target
```

## 客户端run.sh
```
/usr/app/rtty/rtty -I 'T18376xxx' -h 'xxx.40.223.xxx' -p 5915 -a -v -d 'jixun-xxx(test)'  &
```
