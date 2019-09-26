---
layout: post
title: "nginx使用负载均衡所要配置的对外端口"
keywords: ["nginx使用负载均衡所要配置的对外端口"]
description: "nginx使用负载均衡所要配置的对外端口"
category: "NGINX"
tags: ["NGINX"]
---
{% include JB/setup %}

```
server {
        listen       8100 default;
        server_name  localhost;
        charset utf-8;
        access_log /data/logs/8100.log;
        root   /data/www/gushi/;
            if (!-e $request_filename) {
                rewrite  ^(.*)$  /index.php?s=$1  last;
                break;
            }
            root   /data/www/gushi/;
            index  index.html index.htm index.php;
        }
        location ~.*\.(jpg|png|jpeg|ico|bmp)$
        {
          root   /data/www/gushi/;
          #expires 30d;
        }
        #js css缓存一小时
        location ~.*\.(js|css)?$
        {
          root   /data/www/gushi/;
          #expires 30d;
        }
        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        location ~ \.php$ {
            fastcgi_pass   127.0.0.1:9000;
            fastcgi_index  index.php;
            include        fastcgi_params;
            fastcgi_param  TP_ENV  aiqingyuxingzuo;
        }
}
server {
    listen       80;
    server_name test.com;
    charset utf-8;
    access_log /data/logs/gushi.log;
    root   /data/www/gushi/;

    location / {
        if (!-e $request_filename) {
            rewrite  ^(.*)$  /index.php?s=$1  last;
            break;
        root   /data/www/gushi/;
        index  index.html index.htm index.php;
    }

    location ~.*\.(jpg|png|jpeg|ico|bmp)$
    {
      root   /data/www/gushi/;
      #expires 30d;
    }

    #js css缓存一小时
    location ~.*\.(js|css)?$
    {
      root   /data/www/gushi/;
      #expires 30d;
    }

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    location ~ \.php$ {
        fastcgi_pass   127.0.0.1:9000;
        fastcgi_index  index.php;
        include        fastcgi_params;
        fastcgi_param  TP_ENV  jiaoninhuazhuangdaban;
	fastcgi_param  TP_MY_NAME  aliyun-config;
    }
}
```