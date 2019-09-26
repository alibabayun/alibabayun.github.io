---
layout: post
title: "利用NGINX 反向代理到内网"
keywords: ["nginx"]
description: "签名报错"
category: "反向代理"
tags: ["反向代理", "nginx"]
---
{% include JB/setup %}

利用NGINX 反向代理到内网

soj.test.kangzishangcheng.net  解析到腾讯云上，

然后指定内网domain  ：  test.soj.com

```
upstream company_proxy {
    server 固定外网IP:30002;
}   

server {
    listen 80;
    server_name  soj.test.kangzishangcheng.net;

    root   html;
    index  index.html index.htm index.php;

    ## send request back to apache ##
    location / {
        proxy_pass  http://company_proxy;

        #Proxy Settings
        proxy_redirect     off;
        proxy_set_header   Host            test.soj.com;
        proxy_set_header   X-Real-IP        $remote_addr;
        proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        proxy_next_upstream error timeout invalid_header http_500 http_502 http_503 http_504;
        proxy_max_temp_file_size 0;
        proxy_connect_timeout      90;
        proxy_send_timeout         90;
        proxy_read_timeout         90;
        proxy_buffer_size          4k;
        proxy_buffers              4 32k;
        proxy_busy_buffers_size    64k;
        proxy_temp_file_write_size 64k;
   }
}
```

![图片](https://img.alicdn.com/imgextra/i2/1819728314/TB2U7xQpXXXXXaKXFXXXXXXXXXX_!!1819728314.png)