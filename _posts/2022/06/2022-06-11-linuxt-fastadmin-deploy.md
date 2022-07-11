---
layout: post
title: "fastadmin部署的一些问题"
keywords: ["fastadmin"]
description: "fastadmin部署的一些问题"
category: "fastadmin"
tags: ["fastadmin"]
---

## 前言
最近在将fastadmin部署到宝塔时，发现前台能打开，生成的随机后台打不开，提示模板不存在，或是报404


## 解决方法

伪静态参考如下
```
location / {
                if (!-e $request_filename) {
                rewrite ^(.+?\.php)(/.+)$ /$1?s=$2 last;# 加上这一句
                rewrite ^(.*)$ /index.php?s=$1 last;
                break;
            }
          
  }
```

## 参考资料
[https://segmentfault.com/q/1010000021925455/a-1020000022773394](https://segmentfault.com/q/1010000021925455/a-1020000022773394)


## window下如安装宝塔，后台报404
参考 [win10安装新版宝塔，fastadmin后台404 解决办法](https://ask.fastadmin.net/article/33122.html)
