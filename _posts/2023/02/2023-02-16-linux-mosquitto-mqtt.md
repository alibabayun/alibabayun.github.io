---
layout: post
title: "mosquitto用户配置及权限管理"
keywords: ["mqtt"]
description: "mosquitto用户配置及权限管理"
category: "mqtt"
tags: ["mqtt"]
---

## 前言
mosquitto中可以添加多个用户，只有使用用户名和密码登陆服务器才允许用户进行订阅与发布操作。可以说用户机制是mosquitto重要的安全机制，增强服务器的安全性。
用户与权限配置需要修改3处地方：


参考文档：https://www.cnblogs.com/saryli/p/9820532.html
