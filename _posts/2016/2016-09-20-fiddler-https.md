---
layout: post
title: "Fiddler抓取HTTPS设置"
keywords: ["Fiddler抓取HTTPS设置"]
description: "Fiddler抓取HTTPS设置"
category: "Fiddler"
tags: ["Fiddler"]
---
{% include JB/setup %}

注意以下操作的前提是，手机已经能够连上Fiddler，这部分的配置过程简单就不赘述了，可参考：[手机如何连接Fiddler](http://www.jianshu.com/p/54dd21c50f21?utm_campaign=hugo&utm_medium=reader_share&utm_content=note&utm_source=weixin-friends&from=groupmessage&isappinstalled=0) 

如何继续配置让Fiddler抓取到HTTPS协议呢？

一）首先对Fiddler进行设置：打开工具栏->Tools->Fiddler Options->HTTPS

![](https://img.alicdn.com/imgextra/i3/1819728314/TB2aGgaXH2B11BjSsplXXcMDVXa_!!1819728314.png)

选中Capture HTTPS CONNECTs，因为我们要用Fiddler获取手机客户端发出的HTTPS请求，所以中间的下拉菜单中选中from remote clients only。选中下方Ignore server certificate errors.

二）然后，就是手机安装Fiddler证书。

这一步，也就是我们上面分析的抓取HTTPS请求的关键。
操作步骤很简单，打开手机浏览器，在浏览器地址输入代理服务器IP和端口，会看到一个Fiddler提供的页面。

![](https://img.alicdn.com/imgextra/i2/1819728314/TB2ANv.XOnB11BjSsphXXXpaXXa_!!1819728314.png)

接着点击最下方的FiddlerRoot certificate，这时候点击确定安装就可以下载Fiddler的证书了。
下载安装完成好后，我们用手机客户端或者浏览器发出HTTPS请求，Fiddler就可以截获到了，就跟截获普通的HTTP请求一样。
好啦，以上就是关于HTTPS的简介以及Fiddler如何获取HTTPS协议的原理和配置，看到Fiddler整齐划一地截获到HTTP和复杂的HTTPS协议，心里还有点小激动呢。


[原文链接](http://www.jianshu.com/p/54dd21c50f21?utm_campaign=hugo&utm_medium=reader_share&utm_content=note&utm_source=weixin-friends&from=groupmessage&isappinstalled=0)