---
layout: post
title: "go cgo c++交叉编辑"
keywords: ["vm"]
description: "go cgo c++交叉编辑"
category: "go"
tags: ["go"]
---

## 前言
交叉编译其实是相对于本地编译(native build)来说的，我相信大家最开始学习 C/C++ 这些语言的时候，都是在电脑上写程序，然后在电脑上编译生成可执行文件，最后在电脑上运行。程序的编辑——》编译——》运行，整个过程都是在一台 X86 电脑上。

当我们开始接触嵌入式开发后，事情变的不一样了，你在电脑上写程序，在电脑上编译出可执行文件，最后这个可执行文件需要下载到你的开发板上运行。程序最后运行的环境变了，比如你的开发板是基于 Arm 的——程序在 X86 上编辑，编译，最终运行在另一个和 X86 完全不同的架构的 Arm 芯片上。

## 参考资料
[https://cloud.tencent.com/developer/article/1571937](https://cloud.tencent.com/developer/article/1571937)
[Golang : multiple definition of CGO ported package](https://www.coder.work/article/7496938)
