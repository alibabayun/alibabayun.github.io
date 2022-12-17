---
layout: post
title: "Makefile来管理项目"
keywords: ["linux"]
description: "Makefile来管理项目"
category: "linux"
tags: ["linux"]
---

## 概述
一个工程中的源文件不计其数，其按类型、功能、模块分别放在若干个目录中，makefile定义了一系列的规则来指定哪些文件需要先编译，哪些文件需要后编译，哪些文件需要重新编译，甚至于进行更复杂的功能操作，因为 makefile就像一个Shell脚本一样，也可以执行操作系统的命令

## 常用功能
#### 1. makefile之命令包&多行变量
命令包的简单使用---自定义函数
``` bash
all:                    
    @$(cmd)

define cmd
    echo "test define 1"
    echo "test define 2"
    echo "test define 3"
endef
```

#### 2. 关于shell：如何在Makefile目标中使用Bash语法？
从GNU Make文档中，
```
5.3.1 Choosing the Shell
------------------------

The program used as the shell is taken from the variable `SHELL'.  If
this variable is not set in your makefile, the program `/bin/sh' is
used as the shell.
```
因此，将`SHELL := /bin/bash`放在makefile的顶部，您应该会很好。
顺便说一句：您也可以针对一个目标执行此操作，至少对于GNU Make而言。每个目标可以有自己的变量分配，如下所示：
``` bash
all: a b

a:
    @echo"a is $$0"

b: SHELL:=/bin/bash   # HERE: this is setting the shell for b only
b:
    @echo"b is $$0"
```


## make例子
``` sh
SHELL := D:\Programs\Git\bin\bash.exe

BINARY="build/iot_service"
VERSION=1.0.0
export GOARCH=amd64
export GOOS=linux

clear:
	@$(RM) ${BINARY}

build:
	@go build -o ${BINARY} ./

push-test:build
	@$(push_test)
.PHONY: build push-test

define push_test
	ssh root@192.168.1.2 "service  go-iot_service stop;/usr/bin/rm -rf /opt/lc/iot_svr/iot_service"
    scp -r build/* root@192.168.1.2:/opt/lc/iot_svr/
    ssh root@192.168.1.2 "chmod +x /opt/lc/iot_svr/iot_service;service  go-iot_service start;sleep 3;service  go-iot_service status"
endef
```