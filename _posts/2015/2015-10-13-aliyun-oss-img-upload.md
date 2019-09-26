---
layout: post
title: "php使用aliyunOss服务上传图片"
keywords: ["aliyun", "php"]
description: "php使用aliyunOss服务上传图片"
category: "技术分享"
tags: ["aliyun", "oss"]
---

{% include JB/setup %}

##### 什么是oss?

[点击进入阿里云oss官网](http://www.aliyun.com/product/oss/)

阿里云对象存储（Object Storage Service，简称OSS），是阿里云对外提供的海量，安全，低成本，高可靠的云存储服务。用户可以通过调用API，在任何应用、任何时间、任何地点上传和下载数据，也可以通过用户Web控制台对数据进行简单的管理。OSS适合存放任意文件类型，适合各种网站、开发企业及开发者使用。



#### PHP如何将图片上传到阿里云oss？

1. 在控制台得到以下配置信息
   * `OSS_ACCESS_ID` 
   *  `OSS_ACCESS_KEY`
   *  `bucket名称`
2. 设置bucket的读写权限
3. 在`图片处理`里面解析自己的域名
4. 将配置文件配置到`conf.inc.php`中
5. 用`demo.php` 进行测试吧

源码路径：[https://github.com/alibabayun/php_aliyun_oss_upload](https://github.com/alibabayun/php_aliyun_oss_upload)


