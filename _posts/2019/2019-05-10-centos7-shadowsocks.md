---
layout: post
title: "centos下用shadowsocks代理"
keywords: ["代理"]
description: "centos下用shadowsocks代理"
category: "代理"
tags: ["代理"]
---

有时要用到国外的网来拉一些镜像,要作下代理在这里记录下

## 1. 安装shadowsocks
```
yum install python-setuptools && easy_install pip
/usr/bin/pippip install shadowsocks
vi /etc/shadowsocks.json
```

## 2. `vi /etc/shadowsocks.json`文件为
```
{
    "server":"代理ip",
    "server_port":443,
    "local_address": "127.0.0.1",
    "local_port":1080,
    "password":"密码",
    "timeout":300,
    "method":"aes-256-cfb"
}
```

## 3. 启动服务
```
sslocal -c /etc/shadowsocks.json
```

## 4. 重启网卡
```
service network restart
```

## 5. 代理上网设置(当前用户)
```
vi ~/.bash_profile
```
设置
```
# 代理设置
#export proxy="socks5://127.0.0.1:1080"
#export http_proxy=$proxy
#export https_proxy=$proxy
#export ftp_proxy=$proxy
#export no_proxy="localhost, 127.0.0.1, ::1"
export PATH
```
最后使其生效
```
source ~/.bash_profile
```

