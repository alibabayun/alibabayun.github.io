---
layout: post
title: "利用wireshark远程抓包openwrt"
keywords: ["openwrt"]
description: "利用wireshark远程抓包openwrt"
category: "openwrt"
tags: ["openwrt"]
---

### 前言
最近在调试设备时需要在本地远程调试openwrt内的数据

### 操作步骤
1. 打开`git_bash`
2. 进入到`Wireshark`的安装目录
3. 开户ssh免登陆
4. 运行以下代码
```
ssh admin@10.0.0.158 "tcpdump -s 0 -U -n -i br-lan -w - 'not port 22'" | ./wireshark.exe -k -i -
```


### 参考链接
https://blog.csdn.net/yuan9ji/article/details/78546117
