---
layout: post
title: "Golang微信支付V3SDK回调坑"
keywords: ["golang"]
description: "Golang微信支付V3SDK回调坑"
category: "golang"
tags: ["golang"]
---

### 前言
o在使用 微信最新的V3sdk时 接收回调通知的记录 这里使用的是官方文档中的方法1 使用时发现 回调header中返回的序列号和本地不一致 原因是header中的序列号是平台证书序列号 本地证书是商户证书

### 报错
获取平台证书后解密报错：
```
not valid wechatpay notify request: validate verify fail serial=[***************] request-id=[] err=verifty signature with public key err:crypto/rsa: verification error
```

### 问题
开发时要尤其注意：不要在ParseNotifyRequest前获取http数据！！！

仅此记录~

参考：https://blog.csdn.net/zx164997914/article/details/120788014
