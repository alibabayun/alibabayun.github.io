---
layout: post
title: "Linux硬盘挂载、分区、格式化，卸载方法"
keywords: ["linux"]
description: "Linux硬盘挂载、分区、格式化，卸载方法"
category: "linux"
tags: ["linux"]
---
本文简单介绍了下文件系统及其操作（df命令），磁盘分区、格式化，还有最主要是挂载操作。

在这里对“挂载”做个说明，我们都知道文件系统是创建在磁盘上面的，每个文件系统都有独立的inode、block等信息，而这个文件系统要能连接到目录树才能被我们访问。将文件系统与目录树产生关联的操作我们就成为挂载。即将文件系统挂载到某个目录下，该目录为进入该文件系统的入口。

### 参考资料
[https://www.cnblogs.com/zishengY/p/7137671.html](https://www.cnblogs.com/zishengY/p/7137671.html)

### 更改docker image 存放位置
https://jingyan.baidu.com/article/363872ec39403c6e4ba16f26.html