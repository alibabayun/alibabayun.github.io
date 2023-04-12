---
layout: post
title: "home-assistant"
keywords: ["linux"]
description: "home-assistant"
category: "linux"
tags: ["linux"]
---

## 前言
Home Assistant是一款开源的智能家居自动化平台，使用Python编程语言编写。它提供了丰富的组件（例如HomeKit、Alexa、Google Assistant等）以及支持众多的设备和服务，包括各种传感器、灯光、恒温器、摄像头、媒体设备、天气预报、地图等。通过Home Assistant，用户可以方便地控制和监测他们的家庭设备，同时保护他们的隐私。Home Assistant有着强大的社区支持，用户可以从中获取教程、文档、插件和定制化代码。

## 环境安装
[https://zhuanlan.zhihu.com/p/444212384](https://zhuanlan.zhihu.com/p/444212384)

## 安装流程
### 一、hacs安装
1. 进入`docker exec -it homeassistant bash`容器
2. 打开目录`/config/custom_components`下载 `hacs`
参考资料: [https://post.smzdm.com/p/apv88252/](https://post.smzdm.com/p/apv88252/)
（注意：需要能 `ping 通githup.com`）

### 二、小米MIoT设备接入HomeAssistant通用插件教程
[https://zhuanlan.zhihu.com/p/352903303](https://zhuanlan.zhihu.com/p/352903303)
