---
layout: post
title: "php设置前端跨域名访问"
keywords: ["php设置前端跨域名访问"]
description: "php设置前端跨域名访问"
category: "php"
tags: ["php"]
---
{% include JB/setup %} 

php 版

```
// 指定允许其他域名访问
header('Access-Control-Allow-Origin:*');
// 响应类型
header('Access-Control-Allow-Methods:POST');
// 响应头设置
header('Access-Control-Allow-Headers:x-requested-with,content-type');
```


nginx 版

```
add_header Access-Control-Allow-Origin: *;
```