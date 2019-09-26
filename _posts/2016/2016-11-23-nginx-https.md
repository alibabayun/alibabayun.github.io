---
layout: post
title: "搭建https链接"
keywords: ["搭建https链接"]
description: "搭建https链接"
category: "https"
tags: ["https"]
---
{% include JB/setup %} 

#### 搭建https 第三方网站
[进入该网站 https://certbot.eff.org/](https://certbot.eff.org)

1. 选择web服务器和服务器操作系统
2. 根据指导安装软件 centos 7会提示安装 yum install certbot

#### 注意事项:
1. 更改`https`时全站页面的外部链接都必需为 `https`
2. 安装完成之后会在你项目根目录中生成一个`.well-known`的目录 一切的验证都是基于该目录,所以为了安全性，我们不能让它公开访问
3. 证书的更新 该机构颁发的`https`证书的失效时`3个月`，也就是说3个月之后要重新颁发一次 使用`crontab`去定时的执行任务就好了
4. 还有一点需要特别注意，重新获取证书需要使用`well-knonw`下的东西所以不能删除

#### 证书过期问题：
一般为3个月会过期，所以我们每月更新一次证书

```
# centos 7：我这边是每月执行一次 
0 0 1 * * certbot renew --quiet

# centos 6: 有点不同的是wget获取的文件，需要 
0 0 1 * * /your_project/certbot-auto renew --quiet
```

#### nginx 配置如下：

```
server {
    #监听 443 https端口
      listen 443 ssl;
      ssl_certificate      /etc/letsencrypt/live/881389.test.com/fullchain.pem;
      ssl_certificate_key  /etc/letsencrypt/live/881389.test.com/privkey.pem;
    server_name  881389.test.com;
    autoindex on;

    location / {
      root   /data/www/aliyun/881389/;
      index  index.php index.html index.htm default.php default.html default.htm;
      if (!-e $request_filename) {
          rewrite  ^(.*)$  /index.php?s=$1  last;
          break;
      }
    }

    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    location ~ \.php$ {
        root   /data/www/aliyun/881389/;
        fastcgi_pass   127.0.0.1:9000;
        fastcgi_index  index.php;
        #定义系统环境
        fastcgi_param  APP_ENV  develop;
        fastcgi_param  YUNAPP  881389.test.com;
        fastcgi_param  YUNAPPTHEME  default;
        fastcgi_param  YUNSITEID  54;
        fastcgi_param  TP_ENV  881389_online;
          #fastcgi_param  TP_ENV  test;  #项目运行配置的常量及文件
        fastcgi_param  SCRIPT_FILENAME  /data/www/aliyun/881389/$fastcgi_script_name;
        include        fastcgi_params;
    }

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    location ~ /\.ht {
        deny  all;
    }

    # 禁止访问 .well-known
    location ~ /.well-known {
        allow all;
    }
}

# 将http请求转到https
server {
    listen 80;
    server_name 881389.test.com;
    rewrite ^(.*) https://$server_name$1 permanent;
}
```