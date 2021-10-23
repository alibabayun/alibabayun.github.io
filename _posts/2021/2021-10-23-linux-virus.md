---
layout: post
title: "linux kmv病毒"
keywords: ["linux"]
description: "linux "
category: "linux"
tags: ["linux"]
---

## 前言
最近发现线上服务器上cpu占用100%，内存占用比较低不像是挖矿木马，以`polkitd`的用户执行了 `kmv`如下
```
13915 polkitd   20   0  723856  12392   2608 S 800.0  0.0 711:59.27 kmv

```

## 排查
用ps排查在哪里运行的，出现以下
```
ps -ef | grep kmv
/tmp/kmv -o xxx.ddns.net:452 -u x -o xx.133.1.41:452 -u x
```

## 分析
此木马能是机房中了什么木马，前一天window2008环境也出现相同的远程控制病毒

## 清理
先在服务器找出`kmv`的文件，有可疑的文件禁用起来，`kill`掉上面的进程再观察一下