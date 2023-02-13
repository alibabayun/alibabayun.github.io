---
layout: post
title: "海康摄相头对接 srs"
keywords: ["srs"]
description: "海康摄相头对接 srs"
category: "srs"
tags: ["srs"]
---

### 参考资料
参考1： https://blog.csdn.net/Alan_ran/article/details/126739871
参考2： https://github.com/ossrs/srs/issues/3176
参考3： https://github.com/ossrs/srs-gb28181/issues/4

### 海康rtsp
rtsp://admin:passwd@192.168.110.12:554/h264/ch1/main/av_stream

### 从0开始搭建
搭建centos 环境 ：http://mirrors.aliyun.com/centos/7.9.2009/isos/x86_64/?spm=a2c6h.12873639.article-detail.7.64aa2422mqAnOd
这里使用：CentOS-7-x86_64-Minimal-2009.iso

### ffmpeg
```
ffmpeg -re -i demo.flv -c copy -f flv rtmp://43.136.182.190/live/livestream?secret=xxxx
ffmpeg -f flv -listen 1 -i 'rtsp://admin:xxxx@@192.168.110.12:554/h264/ch1/main/av_stream' -c copy 'rtmp://43.136.182.190/live/livestream?secret=9d7c20422d3b43be8a2e6f35dec12dda'
```
