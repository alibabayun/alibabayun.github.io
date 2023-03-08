---
layout: post
title: "设置VMwar电脑上的Ip所在网段一致的问题"
keywords: ["vm"]
description: "设置VMwar电脑上的Ip所在网段一致的问题"
category: "vm"
tags: ["vm"]
---

## 前言
我们为什么要设置VMware虚拟机里的IP网段与电脑主机的IP所在网段一致的问题，当
电脑上安装了VMware的虚拟机后，这个虚拟机就会被所在宿主主机重新分配一个宿主主机下的网段给虚拟机（即宿主主机和虚拟机不在同一个网段），宿主主机与虚拟机之间可以通信，虚拟机之间也可以通信，但是虚拟机与宿主主机所在网段的其他电脑则无法通信。所以为了解决虚拟机与宿主主机所在网段的其他电脑则无法通信的问题，就需要将虚拟机所在的网段也改为与宿主主机一个网段，这样在一个网段内相互之间就能通信了。

## 参考资料
[https://blog.csdn.net/Mr_XiMu/article/details/102829789](https://blog.csdn.net/Mr_XiMu/article/details/102829789)
