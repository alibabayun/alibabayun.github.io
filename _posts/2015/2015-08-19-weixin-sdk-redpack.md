---
layout: post
title: "微信红包php代码分享"
keywords: ["微信", "微信红包源码", "微信高级接口"]
description: "微信红包开发"
category: "技术分享"
tags: ["微信", "微信红包"]
---
{% include JB/setup %}

微信裂变红包&微信红包接口&企业付款

更多接口请查看 [技术集成 - 微信商户平台](https://pay.weixin.qq.com/wiki/doc/api/index.html)

### 版本号
version  v1.1

### 功能
* 微信红包接口
* 企业付款接口
* 裂变红包接口

### 参数：
|参数|说明
|-----|-----------
| mch_billno | 唯一订单号
| act_name   | 名称
| openid     | 红包openid
| amount     | 红包金额
| wishing    | 描述
| sendType   |发送类型
| sendNum    | 发送红包数量（裂变）

### 配置
* 配置 WxPay.pub.config.php 中的文件
* 注意商户证书的路径 为绝对路径

### 注意
* 裂变红包不能传入 `share_imgurl` `watermark_imgurl` `banner_imgurl` 这三个参数，否则会出现签名错误
* 红包金额每次不能少于100分， 裂变红包平均每个不能少于100分
* APPSECRET 与 KEY 注意不能放错,否则也会出现签名错误的情况

github地址：https://github.com/alibabayun/weixin