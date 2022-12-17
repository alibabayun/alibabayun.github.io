---
layout: post
title: "go 交叉编译 cgo_enabled"
keywords: ["go"]
description: "go 交叉编译 cgo_enabled"
category: "go"
tags: ["go"]
---
## 背景
golang的CGO_ENABLED交叉编译(Linux, Windows)

### 示例`build.bat`
``` window shell
set GOARCH=amd64
set GOOS=linux
set CGO_ENABLED=1
set CC=x86_64-linux-musl-gcc
set CXX=x86_64-linux-musl-g++
go build -o build/iot_service ./
```

### makefile 
```
export GOARCH=amd64
export GOOS=linux
export CGO_ENABLED=1
export CC=x86_64-linux-musl-gcc
export CXX=x86_64-linux-musl-g++
build:
	@go build -o iot_service ./
.PHONY: build
```

### 常见错误
[https://blog.csdn.net/weixin_42662249/article/details/116743543](https://blog.csdn.net/weixin_42662249/article/details/116743543)