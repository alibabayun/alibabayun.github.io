---
layout: post
title: "Nginx下路由url重写规则"
keywords: ["Nginx下路由url重写规则"]
description: "Nginx下路由url重写规则"
category: "nginx"
tags: ["nginx"]
---
{% include JB/setup %} 

```
if (!-e $request_filename) 
{
      #旧版文章页支持
      #rewrite ^/article_(\d+)_(.+)\.html$ /trends_cont.php?id=$1&title=$2 last;
      rewrite ^/article_(\d+)_(.+)\.html$ /index/zj_article/id/40001133/gid/400081/sid/44444.html?from=$1 last;
      rewrite ^/article_(.+)\.html$ /trends_cont.php?title=$1 last;
      rewrite ^/v1/article_(\d+)_(.+)\.html$ /v1/trends_cont.php?id=$1&title=$2 last;
      rewrite ^/v1/article_(.+)\.html$ /v1/trends_cont.php?title=$1 last;

      #伪静态支持
      #rewrite ^/([^_]+)_([^\.]+)\.html$ /?controller=$1&action=$2 last;
      #rewrite ^/([^_]+)_([^_]+)\.html$ /?controller=$1&action=$2 last;
      #rewrite ^/(\w+)_(\w+)\.html$ /?controller=$1&action=$2 last;
      rewrite ^/(yunpay)_(index)\.html$ /yunpay/index.html?s=$1 last;
      rewrite ^/(user)_(\w+)\.html$ /user/logo.html?s=$1 last;

      ## REST API support
      rewrite ^/(\w+)$ /?controller=$1&action=index last;
      rewrite ^/(\w+)\/(\w+)$ /?controller=$1&action=index&id=$2 last;
      rewrite ^/(\w+)\/search\/(\w*)$ /?controller=$1&action=index&keyword=$2 last;

      # 20161116更新
      rewrite ^/index/zj_bdwkj_(\d+)\.html$ /index/zj_bdwkj/gid/$1.html last;
      rewrite ^/index/zj_hdyg_(\d+)\.html$ /index/zj_hdyg/gid/$1.html last;
      rewrite ^/index/article_(\d+)_(\d+)_(\d+)\.html$ /index/zj_article/id/$1/gid/$2/sid/$3.html last;
}
```