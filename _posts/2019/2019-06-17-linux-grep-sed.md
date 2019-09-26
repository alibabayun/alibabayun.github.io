---
layout: post
title: "linux用sed查出结果"
keywords: ["linux"]
description: "linux用sed查出结果"
category: "linux"
tags: ["linux"]
---

### linux下取nginx所占用的端口号

```
netstat -tunlp | grep nginx | awk '{print $4}' | sed -nr 's/^[0-9.]+:([0-9]+).*$/\1/p' |uniq
```
得出如下
```
80
443
```

### 记录一个只允许IP段能防问的功能`iptables`
```
#在tcp协议中，禁止所有的ip访问本机的`6377`端口。
iptables -I INPUT -p tcp --dport 6377 -j DROP
#允许172.17.220.0/24访问本机的`6377`端口
iptables -I INPUT -s 172.17.220.0/24 -p tcp --dport 6377 -j ACCEPT
```

### 查一个目录 下24小时以内变动 的文件
```
find /usr -mtime 0
```

### 参考资料
[https://blog.51cto.com/6656395/1900079](https://blog.51cto.com/6656395/1900079)