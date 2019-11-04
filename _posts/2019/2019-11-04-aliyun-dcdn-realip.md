---
layout: post
title: "nginx多层代理，得到真实IP"
keywords: ["nginx"]
description: "nginx多层代理，得到真实IP"
category: "nginx"
tags: ["nginx"]
---

### 背景
最近有一个这样的场景，用到了几层代理分别是
* 1、第一层是cdn,回源层
* 2、第二层是服务器nginx 代理转发层
* 3、第三层是docker层
在最后的业务层我需要在nginx日志里得到`remote_add`真实ip

### 最终解决方案如下：
在上一级代理层nginx添加以下代码
```
    set_real_ip_from 0.0.0.0/0;
    real_ip_header  X-Forwarded-For;
```

### 参考文章
[https://mp.weixin.qq.com/s/DLA05NJkTRp6DbZJDACJtg](https://mp.weixin.qq.com/s/DLA05NJkTRp6DbZJDACJtg)

[https://www.xuliangwei.com/bgx/1366.html](https://www.xuliangwei.com/bgx/1366.html)

