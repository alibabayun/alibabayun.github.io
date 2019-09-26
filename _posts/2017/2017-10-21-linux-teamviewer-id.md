---
layout: post
title: "linux centos 命令行 安装 teamviewer 启动 停止"
keywords: ["linux centos 命令行 安装 teamviewer 启动 停止"]
description: "linux centos 命令行 安装 teamviewer 启动 停止"
category: "teamviewer"
tags: ["linux", "teamviewer"]
---

### 记录下重要的几条命令

```
yum install teamview.***.rpm
teamviewer --info #查看teamview信息  
teamviewer --passwd [PASSWD]   #设置密码  
```

1. 下载`teamview centos`版本，本人喜欢tar.gz版本，但是官网只有rpm版本，附件中即为官网下载的teamview11
官方下载地址： [https://www.teamviewer.com/zhCN/download/linux/](https://www.teamviewer.com/zhCN/download/linux/)
下载后，放到你的目录下，我的是在`/mnt/software`下

2. 安装
说明：用`rpm` 命令安装可能会出现缺少依赖，而导致安装失败
![](http://img.blog.csdn.net/20160824122222484?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
* 会问你是否安装依赖Is this ok [y/N]: ，
* 输入y即可
* 最后出现Complete!表示安装完成

3.启动
默认会安装到/opt/teamview里面，并且安装成功会默认启动
```
cd /opt/teamviewer/tv_bin/
```

用teamviewer --info命令可查看teamview运行状态
![](http://img.blog.csdn.net/20160824123154419?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

如果没有设置密码，TeamViewer ID： 后面会是空的，执行命令 `teamviewer --passwd `你的密码
再次运行`teamviewer --info`命令就可以看到ID了

good luck!

附录：
```
cd /opt/teamviewer/tv_bin  
teamviewer --setup console #设置启动方式为控制台启动  
teamviewer --daemon restart  #重启teamview服务  
teamviewer --info #查看teamview信息  
teamviewer --passwd [PASSWD]   #设置密码  
teamviewer --help  #查看帮助 
```

以yum方式安装，则卸载

以rpm方式安装，则卸载

卸载teamview 

以关键字查看服务

rpm -q teamviewer

卸载rpm -e 查到的程序名

![](http://img.blog.csdn.net/20160824130732965?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)

