---
layout: post
title: "微信小程序抓包https抓包的血泪史"
keywords: ["抓包"]
description: "微信小程序抓包https抓包的血泪史"
category: "抓包"
tags: ["抓包"]
---

最近调试小程序发现无法使用`chales抓包`，为了搞清楚这个问题，找了相关资料分析了下。
在`Android7.0`及以上的系统中，每个应用可以定义自己的`可信CA集`。
默认情况下，应用只会信任系统预装的CA证书，而不会信任用户安装的CA证书

- 安卓系统 7.0 以下版本，不管微信任意版本，都会信任系统提供的证书
- 安卓系统 7.0 以上版本，微信 7.0 以下版本，微信会信任系统提供的证书
- 安卓系统 7.0 以上版本，微信 7.0 以上版本，微信只信任它自己配置的证书列表

我：
* 安卓7.0以下，系统层面不支持CA的限制
* 微信7以下，我猜可能目标版本没有适配到安卓7,所以安卓7的默认信任机制没有发挥出来，可能理解不对
* 微信7以上，微信应该信任的是系统预装正式和自己配置的正式吧，可能理解不对

主要可以参考：[https://testerhome.com/topics/17746](https://testerhome.com/topics/17746)这篇帖子
目前记录下简单的解决方案：
* 1.换7.0以下的手机，我发现我的小米6手机可以的
* 2.换苹果手机，毕竟说安卓7以上提供的限制能力

至于其他安装系统正式，可以看以下参考
* 参考1：[https://testerhome.com/topics/17746](https://testerhome.com/topics/17746)
* 参考2：[https://www.cnblogs.com/c-x-a/p/9070052.html](https://www.cnblogs.com/c-x-a/p/9070052.html)
* 参考3：[https://segmentfault.com/a/1190000018910384?utm_source=tag-newest](https://segmentfault.com/a/1190000018910384?utm_source=tag-newest)
