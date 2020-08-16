---
layout: post
title: "Https(LetsEncrypt) 在iPhone上慢的问题"
keywords: ["frps"]
description: "Https(LetsEncrypt) 在iPhone上慢的问题"
category: "frps"
tags: ["frps"]
---
参考资源：
- https://www.jianshu.com/p/2fb12a80c33f
- https://www.cnblogs.com/duanweishi/p/13321672.html
- https://www.jianlove.com/ocsp-stapling-092.html

## 原因
域名ocsp.int-x3.letsencrypt.org在国内有DNS污染, 导致浏览器过长时间卡在OCSP检查上, 而不同浏览器对待OSCP机制的处理是不一样的, 比如在IE下最多连接2S, 超时则不处理, 在Chrome中可能都不会处理它, 这便导致了在不同浏览器下打开速度有差异.

关于OSCP是什么, 可以查看这篇文章你不在意的HTTPS证书吊销机制.

## 解决方案
开启 nginx 的 ssl_stapling

```
server {
  resolver 8.8.8.8;
  ssl_stapling on;
  ssl_stapling_verify on;
  ...
}
```

每一句都是必要的, 由于域名ocsp.int-x3.letsencrypt.org在国内有DNS污染, 所以修改 resolver 8.8.8.8就好.

> 如果不添加resolver 8.8.8.8则在nginx日志下会出现下面的错误
[error] 4966#4966: OCSP responder timed out (110: Operation timed out) while requesting certificate status, responder: ocsp.int-x3.letsencrypt.org, peer: 64.13.232.149:80, certificate: "/etc/nginx/key/yourdomain.com/fullchain.pem"


## 验证OCSP Response是否开启成功
```
openssl s_client -connect yourdomain.com:443 -servername yourdomain.com -status -tlsextdebug < /dev/null 2>&1 | grep -i "OCSP response"
```

## 如果开启成功会得到响应
```
CSP response: 
OCSP Response Data:
    OCSP Response Status: successful (0x0)
    Response Type: Basic OCSP Response`
```

## 失败会得到
```
OCSP response: no response sent
```

请多执行几次, nginx并不是马上就去获取OCSP Response, 而是当网站被访问之后异步获取, 所以可能前几次请求不会有OCSP Response.