---
layout: post
title: "SD卡如何格式化"
keywords: ["windows"]
description: "SD卡如何格式化"
category: "windows"
tags: ["windows"]
---

### 前言
用SD卡下载程序到串口屏需要先将SD卡格式化，而直接鼠标右键快速格式化这种方式不行，必须通过字符命令行格式化才行，即运行-cmd命令。

### 格式化
```
format/q f:/fs:fat32/a:4096
```
参考：https://blog.csdn.net/wsq_666/article/details/109492631
