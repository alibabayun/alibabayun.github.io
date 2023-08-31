---
layout: post
title: "远程桌面vncserver"
keywords: ["linux"]
description: "远程桌面vncserver"
category: "linux"
tags: ["linux"]
---

### 前言
最后有一个需要远程操作Jetson Xavier ubuntu18.04的远程控制功能，特此记录下

### 安装

要在 Ubuntu 18.04.6 LTS 上设置 VNC 远程桌面控制，您需要按照以下步骤进行操作：

安装 VNC 服务器软件。打开终端，运行以下命令安装 TightVNC Server：
```
sudo apt update
sudo apt install tightvncserver
```
设置 VNC 密码。运行以下命令来设置 VNC 密码：
```
vncserver
```
系统将要求您输入新的 VNC 密码，并确认密码。请记住您设置的密码，稍后将需要用它来连接远程桌面。

停止刚刚启动的 VNC 会话。运行以下命令停止 VNC 服务器：
```
vncserver -kill :1
```
配置 VNC 服务器。运行以下命令编辑 VNC 服务器的配置文件：
```
nano ~/.vnc/xstartup
```
在文件末尾添加以下内容：
```
#!/bin/bash
xrdb $HOME/.Xresources
startxfce4 &
```
您可以根据自己的需求选择其他桌面环境，比如 GNOME 或 KDE。

保存并关闭文件。按下 "Ctrl + X" 键，然后按 "Y" 键确认保存，最后按 "Enter" 键退出编辑器。

启动 VNC 服务器。运行以下命令启动 VNC 服务器：

vncserver
系统将启动一个新的 VNC 会话，并显示会话的端口号（例如 :1）。

配置防火墙。如果您的系统上启用了防火墙，请允许 VNC 服务器的端口通过防火墙。假设您使用的是 UFW 防火墙，运行以下命令允许 VNC 端口通过：

sudo ufw allow 5901
如果您使用的是不同的防火墙软件，请根据该软件的文档进行配置。

现在，您可以使用 VNC 客户端来连接到 Ubuntu 18.04.6 LTS 的远程桌面了。在 VNC 客户端中输入远程计算机的 IP 地址和 VNC 端口号（例如 192.168.1.100:1），然后输入之前设置的 VNC 密码。

请注意，VNC 连接属于不加密的连接，建议在安全网络环境下使用或者通过 SSH 隧道进行安全传输。


### 配置文件
```
#!/bin/sh
# Uncomment the following two lines for normal desktop:
# unset SESSION_MANAGER
# exec /etc/X11/xinit/xinitrc
#[ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup
#[ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources
#xsetroot -solid grey
#vncconfig -iconic &
#x-terminal-emulator -geometry 80x24+10+10 -ls -title "$VNCDESKTOP Desktop" &
#x-window-manager &
unset SESSION_MANAGER
unset DBUS_SESSION_BUS_ADDRESS
[ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup
[ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources
vncconfig -iconic &
xfce4-session &
```

### 启动命令
```
sudo vncserver -kill :1
vncserver -geometry 1920x1080 :1
```

### 参考资料
1. [客户端下载](https://www.realvnc.com/en/connect/download/viewer/)
2. [服务端配置参考](https://blog.csdn.net/qq_37615507/article/details/106278906)
