---
layout: post
title: "Frp后台自动启动的几个方法"
keywords: ["frps"]
description: "Frp后台自动启动的几个方法"
category: "frps"
tags: ["frps"]
---

## 使用systemctl来控制启动
这个方法比较好用，很方便
`sudo vim /lib/systemd/system/frps.service`
在`frps.service`里写入以下内容

```
[Unit]
Description=fraps service
After=network.target syslog.target
Wants=network.target

[Service]
Type=simple
#启动服务的命令（此处写你的frps的实际安装目录）
ExecStart=/your/path/frps -c /your/path/frps.ini

[Install]
WantedBy=multi-user.target

```

## 参考
https://blog.csdn.net/x7418520/article/details/81077652

## 安卓下穿透
* 文档：
https://github.com/fatedier/frp/blob/master/README_zh.md#%E9%80%9A%E8%BF%87%E8%87%AA%E5%AE%9A%E4%B9%89%E5%9F%9F%E5%90%8D%E8%AE%BF%E9%97%AE%E9%83%A8%E7%BD%B2%E4%BA%8E%E5%86%85%E7%BD%91%E7%9A%84-web-%E6%9C%8D%E5%8A%A1

* 下载sdk：
	- centos服务器下载 frp_0.33.0_linux_amd64.tar.gz
	- 安卓 app下载 frp_0.33.0_linux_arm64.tar.gz
