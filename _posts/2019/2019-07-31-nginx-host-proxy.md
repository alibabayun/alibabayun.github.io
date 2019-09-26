---
layout: post
title: "nginx不同端口和域名代理到不同的新的ip地址"
keywords: ["nginx"]
description: "nginx不同端口和域名代理到不同的新的ip地址"
category: "nginx"
tags: ["nginx"]
---

## 背景
最近有一个这样的需求，有4个域名，每个域名的页面内容都不一样，域名分别为
- `http://a.com`   解析ip为 `192.1.1.1`
- `https://a.com`  解析ip为 `192.1.1.1`
- `http://b.com`   解析ip为 `192.1.1.2`
- `https://b.com`  解析ip为 `192.1.1.2`
现需要将这4个迁移，临时代理到新的域名。

## 解决方案如下
1、设置代理ip端口

```
#http://a.com 代理到新的ip
upstream a_com_proxy_new_80 {
    server 192.168.1.151:80;
}
#https://a.com 代理到新的ip
upstream a_com_proxy_new_443 {
    server 192.168.1.151:8443;
}
#http://b.com 代理到新的ip
upstream b_com_proxy_new_80 {
    server 192.168.1.150:80;
}
#https://b.com 代理到新的ip
upstream b_com_proxy_new_80 {
    server 192.168.1.150:8443;
}
```

2、替换旧的nginx配置

80配置端口、参考如下
```
server {
    listen       80;
    server_name   a.com;
    set $project_name 'a_com';
    if ($host = b.com){
        set $project_name 'b_com';
    }

    location / {
        proxy_pass  http://${project_name}_proxy_new_80;
        #Proxy Settings
        proxy_redirect     off;
        proxy_set_header   Host            $host;
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

443配置端口、参考如下
```
server {
    listen       443;
    server_name   a.com;
    set $project_name 'a_com';
    if ($host = b.com){
        set $project_name 'b_com';
    }

    location / {
        proxy_pass  http://${project_name}_proxy_new_443;
        #Proxy Settings
        proxy_redirect     off;
        proxy_set_header   Host            $host;
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