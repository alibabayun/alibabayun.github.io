---
layout: post
title: "smb连接配置"
keywords: ["smb连接配置", "smb连接配置", "smb连接配置"]
description: "smb连接配置"
category: "技术分享"
tags: ["分享"]
---
{% include JB/setup %}

### 一、安装smb

执行命令行：

```
    #sudo apt-get install samba
    #sudo apt-get install smbfs
```

### 二、windows下匿名访问Ubuntu共享文件

使用samba不进行任何设置时，winXP机器可以连接到Ubuntu机器但提示输入用户名密码，此时不论输入什么都不能访问，要实现匿名访问需要做如下设置

1. 修改配置文件smb.conf,共享/share文件：
    
```
       sudo vim /etc/samba/smb.conf
       搜索"security = user"并改为"security = share"，再把这一行前面的注释符"#"去掉。
```
       

2. 重启samba：
`#sudo /etc/init.d/samba restart`
    

3. 需要密码登陆,在`smb.conf`下增加
    
```
[idea_dev_code]
      path = /data/dev
      available = yes
      browseable = yes
      public = yes
      writable = yes
      valid users = alibabayun
      create mask = 0755
      directory mask =0755
      force user = alibabayun
      force group = alibabayun
```