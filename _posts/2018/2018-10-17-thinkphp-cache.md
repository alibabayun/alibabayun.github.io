---
layout: post
title: "阿里云全站加速设置注意点"
keywords: ["cdn"]
description: "阿里云全站加速设置注意点"
category: "cdn"
tags: ["cdn"]
---

### 最近在设置阿里云dcdn时发现回源的流量比率很不正常

反馈后他们后提供了以下参考参考资料

```
两种原因，一种是他的网站没有开启gzip压缩导致，另一种是cdn的缓存命中低导致，可以让他查看下命中率，如果是命中率低，就让他增加目录的缓存，具体看我上面和下面发的文档
```
1.https://help.aliyun.com/knowledge_detail/40123.html?spm=5176.11065259.1996646101.searchclickresult.4690d06cNuDY3a

2.https://help.aliyun.com/knowledge_detail/40115.html?spm=5176.10695662.1996646101.searchclickresult.77c4aa74YGFNcg

### 解决办法　
在thinkphp中如果要缓存要设置
```
C('HTTP_CACHE_CONTROL','public');
```

### 关于Cache-Control: no-cache和no-store
HTTP1.1中启用Cache-Control 来控制页面的缓存与否，这里介绍几个常用的参数：

* no-cache，浏览器和缓存服务器都不应该缓存页面信息；
* public，浏览器和缓存服务器都可以缓存页面信息；
* no-store，请求和响应的信息都不应该被存储在对方的磁盘系统中；
* must-revalidate，对于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时

参考资料:
https://blog.csdn.net/qq_25673113/article/details/52004236

### thinkphp另特别注意一点

#### 分析http头，发现源站每个响应都带着Pragma:no-cache

#### 解决方法：
thinkphp配置文件，设置【'SESSION_AUTO_START'=>false,】，源站中Pragma:no-cache消失。
测试站点html文件经由cdn之后打开情况，都已经命中缓存。

#### 补充：
很多网站开发公司，上线的网站并未设置根目录index.php的define('APP_DEBUG',false);为关闭状态，同样站点的模板缓存等规则不生效。
建议APP_DEBUG设置为false配合上述操作。

参考资料
http://blog.sina.com.cn/s/blog_6264e0aa0102y5bf.html