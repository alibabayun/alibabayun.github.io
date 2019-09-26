---
layout: post
title: "PHP 解析微信用户信息昵称中有特殊字符json无法解析"
keywords: ["PHP"]
description: "PHP 解析微信用户信息昵称中有特殊字符json无法解析"
category: "PHP"
tags: ["PHP"]
---

PHP 解析微信用户信息昵称中有特殊字符，一个点， json 无法解析
获取微信用户信息中的昵称，这个点，php json 解析不了，复制到百度 百度都直接跳到首页了 使用 mb_convert_encoding(,'utf-8','utf-8') 也不行

在 json_decode 之前
```
preg_replace('/[\x00-\x1F\x80-\x9F]/u', '', trim($a));
```
就可以了

参考资料
[https://www.v2ex.com/t/498336](https://www.v2ex.com/t/498336)