---
layout: post
title: "使用ssh-copy-id批量拷贝公钥到远程主机"
keywords: ["linux"]
description: "使用ssh-copy-id批量拷贝公钥到远程主机"
category: "linux"
tags: ["linux"]
---

#### 使用以下命令可以快速ssh免密登陆
```
ssh-copy-id -i ~/.ssh/id_rsa.pub root@remote_ip 
```

## 背景
之前使用vmware vCenter管理虚拟机, 我们在做模版时就将控制节点的公钥放入了虚拟机模版, 因此使用模版新建的虚拟机都可以直接从控制节点免密钥登录的, 能够有效解决虚拟机密码被改了无法登录的问题, 以及通过控制节点来做一些软件安装/配置修改和服务监控等。

最近在`OpenStack`上新建了100多台机器, 这些机器一开始并没有内置公钥, 机器开机后要对他们进行初始化, 如修改yum源和主机名, 常用工具安装, `SELINUX/iptables`禁用, vim设置优化等, 逐一手动修改工作量无疑会大到让人奔溃, 在不熟悉Ansible的情况下, 我们要做的第一步就是将控制节点的公钥拷贝到远程虚拟机上, 但是一个个的执行`ssh-copy-id`还要输入不同的随机密码,也会累死人的。有没有比较好的shell脚本来实现呢? 摸索了一下, 找到了对应的解决办法;

## 详细步骤
1、需要的工具
```
sshpass (添加epel源进行安装)
ssh-copy-id (centos已内置)
```

2、新建文件保存主机名/IP/密码信息
将需要添加公钥的主机名/IP地址/主机密码存入到文本文件`hostsname.txt`中,格式如下:
```
node01 10.0.0.21 9nDvik7w
node02 10.0.0.22 5fDviDEw
node03 10.0.0.23 FiPp2UpR
node04 10.0.0.24 KeMbe57z
node05 10.0.0.25 FElJ3ArM
```

3、使用sshpass实现ssh自动填写密码
使用sshpass将密码传递给`ssh-copy-id`, 使得当需要输入密码时, 能够自动读取变量进行输入并完成验证.
本脚本在原来的基础上添加了一些附加功能, 包括:
* 拷贝本机公钥到远程主机
* 免密钥登录到远程主机设置主机名
* 在远程主机生成ssh-key并将公钥收集到本机
* 将主机列表添加到hosts文件并拷贝到远程主机
* 设置sshd配置文件UseDNS为no以加快ssh连接并拷贝到远程主机
* 设置ssh配置文件禁用远程scp/ssh时询问并拷贝到远程主机
* 汇总所有主机的公钥并拷贝到所有节点,使得能互相免密登录(慎用)

脚本`copy_ssh_id.sh`如下:
```
vim copy_ssh_id.sh 

#!/bin/bash
rm -f ./authorized_keys; touch ./authorized_keys
sed -i '/StrictHostKeyChecking/s/^#//; /StrictHostKeyChecking/s/ask/no/' /etc/ssh/ssh_config
sed -i "/#UseDNS/ s/^#//; /UseDNS/ s/yes/no/" /etc/ssh/sshd_config

cat hostsname.txt | while read host ip pwd; do
  sshpass -p $pwd ssh-copy-id -f $ip 2>/dev/null
  ssh -nq $ip "hostnamectl set-hostname $host"
  ssh -nq $ip "echo -e 'y\n' | ssh-keygen -q -f ~/.ssh/id_rsa -t rsa -N ''"
  echo "===== Copy id_rsa.pub of $ip ====="
  scp $ip:/root/.ssh/id_rsa.pub ./$host-id_rsa.pub
  #cat ./$host-id_rsa.pub >> ./authorized_keys
  echo $ip $host >> /etc/hosts
done

cat ~/.ssh/id_rsa.pub >> ./authorized_keys
cat hostsname.txt | while read host ip pwd; do
  rm -f ./$host-id_rsa.pub
  echo "===== Copy authorized_keys to $ip ====="
  scp ./authorized_keys $ip:/root/.ssh/
  scp /etc/hosts $ip:/etc/
  scp /etc/ssh/ssh_config $ip:/etc/ssh/ssh_config
  scp /etc/ssh/sshd_config $ip:/etc/ssh/sshd_config
  ssh -nq $ip "systemctl restart sshd"
done
```

4、验证是否拷贝成功
在CRT会话窗口中, 勾选使用`Send command to all sessions`选项同时将命令`cat .ssh/authorized_keys`发送到ssh主机:
```
[root@gateway01 ~]# cat .ssh/authorized_keys
ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEA7S0v0xsVaYB1B0NL/tzX0BkFttUWfBqYCL/LokSe3B6fgajY+b5FhxkCElGGvZKdGRQGqL07hcLcDHE3eWldOqv1jJ/rNO/omlogvs0dZwr9vI8QCmb/98ZHKTLrd3iDVMX4WiSTJ1mgxHIKFz6I1K0whcMObioyr8dFHWWTMSx2LDshGEsFQBcNLfAdjMaYE9OOpo05jOxiMaPq8M1oE4sdcJ3yKZHGO1ZzibapUuHiSma2pMbdR2OGC8SSIs5lRif1UUqg9rdsuztaikKpHSVYMrMZuIWW3jaAuJf8wZtnyaKU6y/GDm3H/SD0LWtRE7FUEBIT64aQjptcoOxoYw== root@deploy
```

5、验证是否能够免密钥登录
```
[root@deploy ~]# ssh ngx-gw01
Warning: Permanently added 'ngx-gw01' (RSA) to the list of known hosts.
Last login: Sun Mar 19 22:54:08 2017 from 172.20.224.202
[root@ngx-gw01 ~]#
```
6、注意
第5步中没有提示要求输入`yes`进行确认,是我之前就在`ssh_config`文件中设置了不进行确认:
```
vim /etc/ssh/ssh_config
#Add at the end of file
  StrictHostKeyChecking no
```
或者
```
sed -i '/StrictHostKeyChecking/s/^#//; /StrictHostKeyChecking/s/ask/no/' /etc/ssh/ssh_config   
```

原文转自 ： [https://segmentfault.com/a/1190000009832597?utm_source=debugrun&utm_medium=referral](https://segmentfault.com/a/1190000009832597?utm_source=debugrun&utm_medium=referral)