---
layout: post
title: "如何用Fiddler进行抓包"
keywords: ["代理", "抓包", "微信"]
description: "如何用Fiddler对只允许微信浏览器的网页进行下载抓包"
category: "技术分享"
tags: ["微信", "代理"]
---
{% include JB/setup %}

```
最近在微信发现了一些漂亮的网页让银有剽窃的冲动。因为实在太好看.... 但是这网页又只能在微信浏览器打开，用浏览器模拟微信浏览器还是不行~，
很生气，所以后果很严重。
然后你懂的....用了以下方式分分钟搞定那些被禁止其他浏览器访问的网页！！
Fiddler是一款非常流行并且实用的http抓包工具，它的原理是在本机开启了一个http的代理服务器，然后它会转发所有的http请求和响应，因此，它比一般的firebug或者是chrome自带的抓包工具要好用的多。不仅如此，它还可以支持请求重放等一些高级功能。显然它是可以支持对手机应用进行http抓包的。本文就来介绍下如何用fiddler对手机应用来抓包。
```

### 准备以下工具：Fiddler软件，Android设备

1.启动Fiddler，打开菜单栏中的 Tools > Fiddler Options，打开“Fiddler Options”对话框。
![图1](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/D031C7CB9D144C57A5A085B11C5D34DA)

2.在Fiddler Options”对话框切换到“Connections”选项卡，然后勾选“Allow romote computers to connect”后面的复选框，然后点击“OK”按钮。
![图2](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/F734240CC18B4FF3B519434F8D6523B2)

3.在本机命令行输入：ipconfig，找到本机的ip地址。
![图3](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/A40CC175CF0A4110B76B2CAA2276932A)

4.打开android设备的“设置”->“WLAN”，找到你要连接的网络，在上面长按，然后选择“修改网络”，弹出网络设置对话框，然后勾选“显示高级选项”。
![图4](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/C8450A8EAA664A0B82B988FD7954F967)

5.在“代理”后面的输入框选择“手动”，在“代理服务器主机名”后面的输入框输入电脑的ip地址，在“代理服务器端口”后面的输入框输入8888，然后点击“保存”按钮。
![图5](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/1D592870BB7546DCAC0BAC124D97A03C)

6.然后启动android设备中的浏览器，访问百度的首页，在fiddler中可以看到完成的请求和响应数据。
![图6](http://note.youdao.com/yws/public/resource/882ecf6738e76baec26fb938a6a6ca7a/A538AAA282834867A4891C9DD22E2D97)
