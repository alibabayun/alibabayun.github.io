---
layout: post
title: "Goland的Terminal使用Git Bash"
keywords: ["idea"]
description: "Goland的Terminal使用Git Bash"
category: "idea"
tags: ["idea"]
---

## 背景
indows下Terminal默认的使用的是系统自带的cmd，功能实在太少，用起来远不如Git Bash来得方利和高效。
其实要在Goland的Terminal中使用Bash设置起来也很简单，设置位置在`Settings > Tools > Terminal > Shell_path`。如图：
![](https://img-blog.csdn.net/2018102300554777?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0plZmZpZA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 方案
我这里是设置为`"D:\APP\Git\bin\sh.exe" --login -i，"D:\APP\Git\bin\sh.exe" `这个是我Git安装目录下sh.exe执行文件所在的位置，请设置为自己的实际对应目录；--login -i这是启动设置。
设置完成后重新开一个Terminal就可以用上了。如图：

原文链接：https://blog.csdn.net/Jeffid/article/details/83281273