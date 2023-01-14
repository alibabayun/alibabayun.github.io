---
layout: post
title: "双网卡使用指定网卡上网"
keywords: ["windows"]
description: "双网卡使用指定网卡上网"
category: "windows"
tags: ["windows"]
---

## 场景：
电脑有两块网卡，都可以上网：
一块有线网卡连接内网（网关：10.10.27.1） 一块无线网卡连接无线网（网关：10.12.0.1）
![](https://img-blog.csdnimg.cn/20200712174011382.bmp?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MzU0Mzg4Mg==,size_16,color_FFFFFF,t_70)



## 检验命令
```
tracert -d myjd.jd.com
```

参考资料[https://blog.csdn.net/weixin_43543882/article/details/107302446](https://blog.csdn.net/weixin_43543882/article/details/107302446)

