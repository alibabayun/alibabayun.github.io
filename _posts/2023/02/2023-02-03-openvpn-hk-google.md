---
layout: post
title: "openvpn客户端指定网关"
keywords: ["openvpn"]
description: "openvpn客户端指定网关"
category: "openvpn"
tags: ["openvpn"]
---

## 前言
MAC 添加路由的命令

### Mac
mac route命令同时访问内外网 
```
sudo route -n add -net 192.168.0.0 -netmask 255.255.255.0 192.168.5.254 
sudo route -n add -net 192.168.3.0 -netmask 255.255.255.0 192.168.5.254 
sudo route -n add -net 192.168.2.0 -netmask 255.255.255.0 192.168.5.254
```

### Windows route命令同时访问内外网 
```
route add -p 192.168.3.0 mask 255.255.255.0 192.168.5.254 
route add -p 192.168.0.0 mask 255.255.255.0 192.168.5.254 
route add -p 192.168.2.0 mask 255.255.255.0 192.168.5.254
```
## openvpn服务端配置
参考资料：https://blog.csdn.net/joshua317/article/details/120245443
```
push "route 10.110.0.0 255.255.255.0"
push "net_gateway"
```

## 示例ip.me
路由添加
```
sudo route add -net 98.159.0.0 10.0.0.1
sudo route add -net 172.217.0.0 10.0.0.1
```

路由删除
```
sudo route delete -net 212.102.35.236 
```

测试命令
```
traceroute -n 212.102.35.236    
curl ip.me
```