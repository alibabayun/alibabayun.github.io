---
layout: post
title: "报错php_network_getaddresses"
keywords: ["linux"]
description: "报错php_network_getaddresses"
category: "linux"
tags: ["linux"]
---

## 背景
最近有个业务，服务器正常，但有部分用户打开出现以下报错！
```
SQLSTATE[HY000] [2002] php_network_getaddresses: getaddrinfo failed: Name or service not known
```
网上查一波资料后，得到的结果是【php无法连接mysql实例】,但一直我们都是用阿里云的，服务器和rds.之前都是正常的，心想难道升级了，有影响。于是，开始提工单。

## 提工单
1.阿里云工程师结出的建议是让我参考以下方式，先检查下：

[https://help.aliyun.com/knowledge_detail/96028.html ](https://help.aliyun.com/knowledge_detail/96028.html )

2.第一次，检查DNS是否是否是按文档里设置
```
cat /etc/resolv.conf 
```
但按上面的设置修改了，但是还是出现刚才那个问题。

2.第二次，检查系统日志
```
tail -f /var/log/messages
```
![](https://ww1.sinaimg.cn/large/005SXJ9Agy1fz0diyfl0aj30sx0aywh2.jpg)

发现里面有很多的`kernel: TCP: time wait bucket table overflowt`

## 解决方案
### 参考资料
[https://help.aliyun.com/knowledge_detail/43225.html](https://help.aliyun.com/knowledge_detail/43225.html)

注意修改配置后需要执行sysctl -p 使配置生效 

### 操作步骤
```
阿里云工程师  :    这个不是根据CPU来的，建议：
1. 执行命令 netstat -anp |grep tcp |wc -l统计 TCP 连接数。
2. 对比 /etc/sysctl.conf 配置文件的 net.ipv4.tcp_max_tw_buckets 最大值，看是否有超出情况。
3. 执行命令 vi /etc/sysctl.conf，查询 net.ipv4.tcp_max_tw_buckets 参数。如果确认连接使用很高，容易超出限制。
4. 根据实际情况适当调高参数 net.ipv4.tcp_max_tw_buckets，扩大限制。
5. 执行命令 # sysctl -p 使配置生效。  
```
### 完全解决
```
工程师 XXX 号 :   
1.我看到还有OOM的情况，建议确认下是不是业务量增加了，内存不够使用了

2.当前值已经到了23980，您调整15000自然是没有效果的，建议直接设置为65535
```