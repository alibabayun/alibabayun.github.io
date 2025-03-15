---
layout: post
title: "nginx在cdn加鉴权跳uploads"
keywords: ["nginx"]
description: "nginx在cdn加鉴权跳uploads"
category: "nginx"
tags: ["nginx"]
---


### 前言

nginx在cdn加鉴权跳uploads

### 阿里云cdn A鉴权

```
your-private-key
```

### 参考链接
```
# 在http块中添加
map $uri $cdn_uri {
    ~^/uploads/(.*)$ $1;
}

## 代理处


# 阿里云cdn A鉴权
location ^~ /uploads/ {
    content_by_lua_block {
        local uri = ngx.var.uri
        local timestamp = ngx.time() + 1800
        local private_key = "your-private-key"  -- 替换成你的密钥

        -- 构建签名字符串（不包含参数的URI路径）
        local string_to_sign = uri .. "-" .. timestamp .. "-0-0-" .. private_key

        -- 计算MD5
        local md5 = ngx.md5(string_to_sign)

        -- 构建CDN URL并重定向
        local cdn_url = "https://mpcdn.xxx.com" .. uri .. "?auth_key=" .. timestamp .. "-0-0-" .. md5

        ngx.redirect(cdn_url, 302)
    }
}
# 无鉴权
# rewrite ^/uploads/(.*)$ http://mpcdn.xxx.com/uploads/$1 permanent;
```
