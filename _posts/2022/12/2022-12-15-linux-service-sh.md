---
layout: post
title: "Go项目从零部署到服务器"
keywords: ["go"]
description: "Go项目从零部署到服务器"
category: "go"
tags: ["go"]
---

### 一、本地配置
#### `makefile`的使用
windows参考[windows下goland中makefile的使用](https://blog.csdn.net/weixin_45629546/article/details/117285235)

##### makefile文件参考如下
```shell

#打包程序名称
APP_NAME="miniprogram_service"
#线上路径
SERVER_PATH="/opt/lc/miniprogram/"

BINARY="build/${APP_NAME}"
VERSION=1.0.0
export GOARCH=amd64
export GOOS=linux
#shell环境 window参考如下
SHELL := D:\Programs\Git\bin\bash.exe

clear:
	@$(RM) ${BINARY}

build:
	@go build  -o ${BINARY} ./

#发布测试环境
push-test:clear build
	@$(call pushServer,"192.168.1.111")

#发布到正式环境
push-pro:clear build
	@$(call pushServer,"114.114.114.114")

.PHONY: build push-test push-pro

define pushServer
	ssh root@$(1) "service  go-${APP_NAME} stop;"
    scp -r build/* root@$(1):${SERVER_PATH}
    ssh root@$(1) "chmod +x ${SERVER_PATH}${APP_NAME};service  go-${APP_NAME} start;sleep 3;service  go-${APP_NAME} status"
endef
```


### 二、配置服务器上 `Service` 文件
可执行文件放在`/server_dir/user-manager/`下
首先新建个Service文件，名称根据项目来取

#### 命令：
```
touch /lib/systemd/system/go-project-name.service
```

#### `vi go-project-name.service`进入编辑该文件

``` shell
[Unit]
Description=项目名称:8120 xxx_service api
 
[Service]
Type=simple
Restart=always
RestartSec=5s
ExecStart=/bin/sh -c "/opt/lc/xxx/xxx_service >> /opt/lc/xxx/nohop.log 2>&1"
WorkingDirectory=/opt/lc/xxx/
 
[Install]
WantedBy=multi-user.target
```

### 三、常见问题
#### ExecStart是go可执行文件的路径

WorkingDirectory要注意，如果程序中使用了相对路径来加载一些配置文件，如果在Service中没有配置WorkingDirectory，默认是根路径，所以配置文件就从根路径来寻找，会造成一些意向不到的情况

PS：如果遇到启动不起来，报错原因为：Main process exited, code=exited, status=200/CHDIR

这种情况下，一般就是ExecStart和WorkingDirectory这两个的路径设置有问题

#### 运行Service
```
service go-project-name start
service go-project-name stop
service go-project-name status
service go-project-name restart
```

#### 开机启动
```
service glass-api enable
```

该命令本人执行失败了，需要使用systemctl命令。
systemd是Linux系统最新的初始化系统(init),作用是提高系统的启动速度，尽可能启动较少的进程，尽可能更多进程并发启动。
systemd对应的进程管理命令是systemctl，systemctl命令兼容了service）

参考: https://blog.csdn.net/Bole_b/article/details/117568539