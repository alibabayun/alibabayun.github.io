---
layout: post
title: "使用Nginx过滤网络爬虫"
keywords: ["nginx"]
description: "使用Nginx过滤网络爬虫"
category: "nginx"
tags: ["nginx"]
---
{% include JB/setup %}

### 使用Nginx过滤网络爬虫

现在的网络爬虫越来越多，有很多爬虫都是初学者写的，和搜索引擎的爬虫不一样，他们不懂如何控制速度，结果往往大量消耗服务器资源，导致带宽白白浪费了。

其实`Nginx`可以非常容易地根据`User-Agent`过滤请求，我们只需要在需要URL入口位置通过一个简单的正则表达式就可以过滤不符合要求的爬虫请求：

```
location / {
    if ($http_user_agent ~* "python|curl|java|wget|httpclient|okhttp") {
        return 503;
    }
    # 正常处理
    ...
}
```
变量`$http_user_agent`是一个可以直接在location中引用的`Nginx`变量。~*表示不区分大小写的正则匹配，通过python就可以过滤掉80%的Python爬虫。