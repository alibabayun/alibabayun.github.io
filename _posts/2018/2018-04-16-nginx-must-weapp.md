---
layout: post
title: "网站有多个webapp的配置"
keywords: ["nginx"]
description: "网站有多个webapp的配置"
category: "nginx"
tags: ["nginx"]
---
{% include JB/setup %}

### 网站有多个webapp的配置

> 当一个网站功能越来越丰富时，往往需要将一些功能相对独立的模块剥离出来，独立维护。这样的话，通常，会有多个webapp。
> 举个例子：假如www.helloworld.com站点有好几个webapp，finance（金融）、product（产品）、admin（用户中心）。访问这些应用的方式通过上下文(context)来进行区分:
> www.helloworld.com/finance/
> www.helloworld.com/product/
> www.helloworld.com/admin/
> 我们知道，http的默认端口号是80，如果在一台服务器上同时启动这3个webapp应用，都用80端口，肯定是不成的。所以，这三个应用需要分别绑定不同的端口号。
> 那么，问题来了，用户在实际访问www.helloworld.com站点时，访问不同webapp，总不会还带着对应的端口号去访问吧。所以，你再次需要用到反向代理来做处理。
> 配置也不难，来看看怎么做吧：

```
http {
    #此处省略一些基本配置
    
    upstream product_server{
        server www.helloworld.com:8081;
    }
    
    upstream admin_server{
        server www.helloworld.com:8082;
    }
    
    upstream finance_server{
        server www.helloworld.com:8083;
    }

    server {
        #此处省略一些基本配置
        #默认指向product的server
        location / {
            proxy_pass http://product_server;
        }

        location /product/{
            proxy_pass http://product_server;
        }

        location /admin/ {
            proxy_pass http://admin_server;
        }
        
        location /finance/ {
            proxy_pass http://finance_server;
        }
    }
}
```