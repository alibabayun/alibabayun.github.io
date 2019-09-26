---
layout: post
title: "用docker块速搭建l2tp VPN服务器"
keywords: ["VPN"]
description: "用docker块速搭建l2tp VPN服务器"
category: "VPN"
tags: ["VPN"]
---
### 使用docker能快速地搭建VPN
	docker就像一个大仓库, 我们只要从仓库中取出我们需要的应用(vpn服务器)运行起来就能用了。服务器是CentOS7

## 一. 搭建vpn服务器

1. 获取 l2tp 的镜像
```
docker pull fcojean/l2tp-ipsec-vpn-server
```

2. 配置PSK，用户名和密码。先创建配置文件vpn.env
```
vim vpn.env
```

3. 将一下配置复制到vpn.env文件中。该配置psk为“abcdefgpass”，两个用户名user1和user2，密码都为123456
```
VPN_IPSEC_PSK=abcdefgpass
VPN_USER_CREDENTIAL_LIST=[{"login":"user1","password":"123456"},{"login":"user2","password":"123456"}] 
```
注意上面`abcdefgpass`为IPsec 密钥

4. 加载 IPsec NETKEY 内核模块
```
sudo modprobe af_key
```
5. 执行如下命令（请完整复制粘贴）启动镜像，该命令加载env文件做配置文件，并将对应端口和服务器的端口做绑定。
注意：在执行该命令时当前目录下必须有之前创建的vpn.env文件，否则会报错。
```
docker run \
    --name vpn-server \
    --env-file ./vpn.env \
    -p 500:500/udp \
    -p 4500:4500/udp \
    -v /lib/modules:/lib/modules:ro \
    -d --privileged \
    fcojean/l2tp-ipsec-vpn-server
```
6. 查看当前的vpn服务是否正常启动
```
docker logs vpn-server
```
有如下输出则表示已正常启动vpn服务器。
```
================================================
IPsec VPN server is now ready for use!
Connect to your new VPN with these details:
DNS: 8.8.8.8
Server IP: XX.XX.XX.XX
IPsec PSK: abcdefgpass
Users credentials :
Login : user1 Password : 123456
Login : user2 Password : 123456
Write these down. You'll need them to connect!
Setup VPN Clients: https://git.io/vpnclients
================================================
Redirecting to: /etc/init.d/ipsec start
Starting pluto IKE daemon for IPsec: Initializing NSS database
```
7. 关闭防火墙，先查看防火墙状态

### 除了关闭防火墙，云服务器还需要配置访问规则, 即开放端口, 一定要记得开放500端口和4500端口, 这两个端口是vpn使用的.

## 二. 配置客户端


参考资料：
[https://blog.csdn.net/Unseenblade/article/details/80957931](https://blog.csdn.net/Unseenblade/article/details/80957931)
