---
layout: post
title: "Nginx限制访问速率和最大并发连接数模块"
keywords: ["nginx"]
description: "Nginx限制访问速率和最大并发连接数模块"
category: "nginx"
tags: ["nginx"]
---

使用Nginx限制单个IP的并发连接数能够减少一些采集程序或者DDOS的攻击。

* 参考资料1: [https://www.cnblogs.com/wjoyxt/p/6128183.html](https://www.cnblogs.com/wjoyxt/p/6128183.html)
* 参考资料2: [http://blog.51cto.com/storysky/642970](http://blog.51cto.com/storysky/642970)
* 参考资料3: [https://blog.csdn.net/u013160024/article/details/70182911](https://blog.csdn.net/u013160024/article/details/70182911)

```
upstream monitor_server {
    server 192.168.1.1:80 weight=5; #负载ip
    server 192.168.1.2:80 weight=5; #负载ip
}
#参考资料 https://www.cnblogs.com/wjoyxt/p/6128183.html
limit_conn_log_level error;
limit_conn_status 503;

limit_conn_zone $binary_remote_addr zone=one:10m;#单个ip最大值
limit_conn_zone $server_name zone=perserver:10m;#全站最大值

#参考资料 http://blog.51cto.com/storysky/642970
limit_req_zone  $binary_remote_addr  zone=req_one:10m rate=1r/s;#漏桶算法

server {
    listen 80;
    server_name *.test.com;
    limit_conn  one  3;  
    limit_conn perserver 2000;
    limit_rate 200k;
    limit_req   zone=req_one  burst=12 nodelay;
    location / {
        proxy_redirect off;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_pass http://monitor_server;
    }
    access_log /data/logs/wx.test.com_access.log;
    error_log /data/logs/wx.test.com_error.log;
}
```