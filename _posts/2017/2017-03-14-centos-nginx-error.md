---
layout: post
title: "Primary script unknown"
keywords: ["centos", "Nginx"]
description: "Nginx error: “Primary script unknown” while reading response header from upstream"
category: "Nginx"
tags: ["Nginx", "centos"]
---
{% include JB/setup %}

Nginx error: “Primary script unknown” while reading response header from upstream

当报以上错误时
有两个原因

1、`php-fpm.conf`用户没配置正确
change www.conf

```
user = nginx 
group = nginx
```

2、nginx 的	script_filename 没配置正确
change fastcgi_param SCRIPT_FILENAME /scripts$fastcgi_script_name; into

```
fastcgi_param  SCRIPT_FILENAME  $document_root$fastcgi_script_name;
```