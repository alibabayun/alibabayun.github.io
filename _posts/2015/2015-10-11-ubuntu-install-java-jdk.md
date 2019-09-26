---
layout: post
title: "怎样在Ubuntu 14.04中安装java"
keywords: ["ubuntu", "linux", "java"]
description: "怎样在Ubuntu 14.04中安装Java"
category: "技术分享"
tags: ["ubuntu", "java"]
---
{% include JB/setup %}


原文来自：
[https://linux.cn/article-3792-1.html](https://linux.cn/article-3792-1.html)

####怎样在Ubuntu 14.04中安装Java

最近安装一个代码压缩工具 `yuicompressor-2.4.7.jar`, 因为要依赖于java环境所以在网上找了下，发现还是公社的教程实用特此记录一下，方便以后再找

#### 检查Java是否已经安装在Ubuntu上

```c
java -version
```

####如果你看到像下面的输出，这就意味着你并没有安装过Java:

```c
The program ‘java’ can be found in the following packages:
* default-jre
* gcj-4.6-jre-headless
* openjdk-6-jre-headless
* gcj-4.5-jre-headless
* openjdk-7-jre-headless
Try: sudo apt-get install
```

####在Ubuntu和Linux Mint上安装JRE
打开终端，使用下面的命令安装JRE：

```c
sudo apt-get install default-jre
```

####在Ubuntu和Linux Mint上安装Oracle JDK
使用下面的命令安装，只需一些时间，它就会下载许多的文件，所及你要确保你的网络环境良好：

```c
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer
sudo apt-get install oracle-java8-set-default
```

