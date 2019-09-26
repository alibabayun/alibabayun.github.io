---
layout: post
title: "linux rm删除含有特殊符号目录或者文件"
keywords: ["linux rm删除含有特殊符号目录或者文件"]
description: "linux rm删除含有特殊符号目录或者文件"
category: "linux"
tags: ["linux"]
---
{% include JB/setup %}


想要删除time$1.class，用rm time$1.class是不行的，可以用 rm time"$"1.class 删掉

假设Linux系统中有一个文件名叫“-polo”。如果用户想删除它，按照一般的删除方法在命令行中输入“rm -polo”命令后，界面会提示是“无效选项”(invalid option)。
原因是Linux把文件名的第一个字符为“-”当作选项了。用户可以使用“--”符号来解决这个问题。输入“rm -- -polo”命令便可顺利删除名为“-polo”的文件。
如果是其它特殊字符的话可以在特殊字符前加一个“”符号，或者用双引号把整个文件名括起来都可以。


```
rm -rf -- '特殊文件目录名ａｓｆｆａｓｄｆ'
```