---
layout: post
title: "云存储的数据迁移到OSS"
keywords: ["OSS"]
description: "云存储的数据迁移到OSS"
category: "OSS"
tags: ["OSS"]
---
## 背景
最近有个需求，要两个不同的阿里云账号的oss进行迁移，提工单问了下了阿里云的客户，发现他们已有一个很完整的工具，包含其它云存储的迁移方案，在此记录下

## 概述

OssImport工具可以将本地、其它云存储的数据迁移到OSS，它有以下特点：

- 支持的丰富的数据源，有本地、七牛、百度BOS、AWS S3、Azure Blob、又拍云、腾讯云COS、金山KS3、HTTP、OSS等，并可根据需要扩展；
- 支持断点续传；
- 支持流量控制；
- 支持迁移指定时间后的文件、特定前缀的文件；
- 支持并行数据下载、上传；
- 支持单机模式和分布式模式，单机模式部署简单使用方便，分布式模式适合大规模数据迁移。

## 工具

阿里云oss迁移工具：
[https://help.aliyun.com/document_detail/56990.html?spm=5176.doc44075.2.12.0HAvsy](https://help.aliyun.com/document_detail/56990.html?spm=5176.doc44075.2.12.0HAvsy)