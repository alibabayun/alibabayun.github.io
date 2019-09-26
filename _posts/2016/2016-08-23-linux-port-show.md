---
layout: post
title: "linux查看占用端口"
keywords: ["linux查看占用端口"]
description: "linux查看占用端口"
category: "port"
tags: ["port"]
---
{% include JB/setup %}

```
netstat -tunlp | grep 80
```

```
# netstat -tunlp |grep 80
tcp        0      0 0.0.0.0:80              0.0.0.0:*               LISTEN      1672/nginx: master  
tcp        0      0 0.0.0.0:8080            0.0.0.0:*               LISTEN      1582/java           
tcp6       0      0 :::80                   :::*                    LISTEN      1672/nginx: master  
tcp6       0      0 :::9080                 :::*                    LISTEN      26771/docker-proxy 
```