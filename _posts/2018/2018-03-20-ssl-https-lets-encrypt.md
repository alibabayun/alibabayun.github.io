---
layout: post
title: "Let’s Encrypt 终于发布Wildcard（泛域名）证书了"
keywords: ["https"]
description: "Let’s Encrypt 终于发布Wildcard（泛域名）证书了"
category: "https"
tags: ["https"]
---
{% include JB/setup %}

记录一些参考资料
* [acme.sh](https://github.com/Neilpang/acme.sh)
```
#本博客生成方法
vi dnsapi/dns_ali.sh #注意要先将阿里云的appid 与key 编辑此文件中
./acme.sh --issue --dns dns_ali -d alibabayun.github.io 
```
* [dnsapi](https://github.com/Neilpang/acme.sh/tree/master/dnsapi)
* [dns alias ](https://github.com/Neilpang/acme.sh/wiki/DNS-alias-mode)
* [le-dns](https://github.com/alibabayun/scripts/tree/master/le-dns)

nginx 配置示例
```
server {
    listen 443;
    server_name you_domain;
    ssl on;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_ciphers EECDH+CHACHA20:EECDH+CHACHA20-draft:EECDH+AES128:RSA+AES128:EECDH+AES256:RSA+AES256:EECDH+3DES:RSA+3DES:!MD5;
    ssl_session_cache builtin:1000 shared:SSL:10m;
    resolver 8.8.8.8 8.8.4.4 valid=300s;
    resolver_timeout 5s;
    ssl_prefer_server_ciphers on;
    ssl_certificate /root/https_auto_create/certs/you_domain/fullchain.pem;
    ssl_certificate_key /root/https_auto_create/certs/you_domain/privkey.pem;
    ssl_session_timeout 5m;
}
```

如题，Let's Encrypt经过几轮的跳票后终于发布Wildcard（泛域名）证书了，民间叫：野卡

Let's Encrypt是一个于2015年三季度推出的数字证书认证机构，旨在以自动化流程消除手动创建和安装证书的复杂流程，并推广使万维网服务器的加密连接无所不在，为安全网站提供免费的SSL/TLS证书。且已经获得谷歌、Mozilla、微软等主要浏览器厂商的根授信。

![](http://qirexiaoshuo.oss-cn-shanghai.aliyuncs.com/book/241735-5ab0c92c4bbe0.jpg)

以下是Let's Encrypt官方原文
```
ACME v2和Wildcard通配证书正式发布

我们很高兴地宣布ACMEv2和通配符证书正式发布！通过今天的新功能，我们正在继续打破在Web上采用HTTPS的障碍，让每个网站更容易获取和管理免费的SSL证书。

ACMEv2 是我们的ACME协议的更新版本，它已通过IETF标准流程，同时考虑到行业专家和其他组织可能希望在某天使用ACME协议进行证书颁发和管理的反馈意见。

通配符证书允许您使用单个证书来保护域的所有子域。在某些情况下，通配符证书可以使证书管理更容易，我们希望解决这些情况，以帮助使Web达到100％的HTTPS。对于大多数用例，我们仍然推荐使用非通配符证书。

通配符证书只能通过ACMEv2获得。为了将ACMEv2用于通配符或非通配符证书，您需要一个已更新以支持ACMEv2的客户端。我们的意图是将所有用户转换为ACMEv2，尽管我们还没有为我们的ACMEv1 API设置启用日期。

另外，通配符域必须使用DNS-01质询类型进行验证。这意味着您需要修改DNS TXT记录才能演示对域的控制以获得通配符证书。

我们对100％HTTPS Web的前景感到非常兴奋，我们正在努力实现这一目标。
```

