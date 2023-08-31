---
layout: post
title: "Kali linux"
keywords: ["linux"]
description: "Kali linux"
category: "linux"
tags: ["linux"]
---

### 前言
Kali Linux是一种基于Linux的操作系统，主要用于网络安全和渗透测试。它是由前身为BackTrack Linux的团队开发和维护的。Kali Linux集成了大量的安全工具，并提供了广泛的功能和特性，用于评估系统的安全性和执行各种安全测试。其目的是帮助安全专业人员、渗透测试者和黑客测试和保护计算机系统的安全。Kali Linux被广泛应用于安全领域，包括漏洞评估、渗透测试、数字取证等。

### 参考资料
1. [Kali linux 全部版本镜像下载](http://old.kali.org/kali-images/)

2. [网络攻防——kali操作系统基本使用](https://blog.csdn.net/weixin_46560512/article/details/123327771)

3. [Kali Linux 2020.1 修改系统语言](https://blog.csdn.net/weixin_46192679/article/details/104694790)

4. [密码库](https://github.com/danielmiessler/SecLists)

5. [在 Kali Linux 下实战 Nmap（网络安全扫描器）](https://linux.cn/article-7960-1.html)

5. [ALI更换国内源（2022年全新）](https://blog.csdn.net/fingue/article/details/127096363)

6. [kali linux下sqlmap使用教程](https://blog.csdn.net/weixin_52084568/article/details/123839776)

7. [黑客工具之hydra详细使用教程](https://zhuanlan.zhihu.com/p/397779150)

### 常用命令
```
# 扫密码
hydra -l root -P UserPassCombo-Jay.txt -t 1 -vV -e ns 192.168.110.26 ssh5
# 扫ip
nmap -sP 192.168.110.1/24
# 扫端口
nmap 192.168.110.253
# 扫端口加服务
nmap -sV 192.168.110.26
```


### vm网络连接
1. 可以将多个网卡先在宿主机桥接
2. 网络连接 选择 NAT模式（N），就可以在虚拟机内打开 上流本机电脑 和 vpn
