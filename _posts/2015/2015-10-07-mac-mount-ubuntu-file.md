---
layout: post
title: "将 Mac OS X 上的目录挂载到 Ubuntu 的方法"
keywords: ["mac", "mount", "ubuntu"]
description: "将 Mac OS X 上的目录挂载到 Ubuntu 的方法"
category: "技术分享"
tags: ["mac", "mount"]
---
{% include JB/setup %}

###将 Mac OS X 上的目录挂载到 Ubuntu 的方法

####打开mac文件共享功能

#####开启共享服务

1. 进入系统偏好设置中的共享选项。
2. 勾中文件共享（如下图），之后右边的文件共享的绿灯会点亮，并显示“文件共享：打开”。


######添加共享目录

1. 点击在文件共享界面（如下图）中右边的共享文件夹下的＋号。
2. 在出现的窗口中找到你要共享的目录，点击增加。
3. 之后在右边的用户里，进行对该目录的访问权限设置。

![图片加载中](http://sfault-image.b0.upaiyun.com/397/347/3973471493-54d9b87f95219_articlex)

#####共享选项设置

* AFP 是 apple 的文件共享协议，如果多台mac可以选择
* 如果共享到 windows 等非 apple 的机子，选择 smb 的 win 共享协议。
* smb 协议需要在选项中添加用户，可以到账户那新建一个用户，设其角色为共享角色。

![图片加载中](http://sfault-image.b0.upaiyun.com/180/647/180647197-54d9b95702187_articlex)

![图片加载中](http://sfault-image.b0.upaiyun.com/174/363/1743632550-54d9b9df75667_articlex)

将 Mac OS X 目录挂载到 ubuntu

```c
#!/bin/bash 
mount -t cifs --verbose -o username=share,password="share",vers=2.1,sec=krb5 //192.168.31.229/share /mac
```
