---
layout: post
title: "Win10系统开机启动文件夹路径的方法"
keywords: ["windows"]
description: "Win10系统开机启动文件夹路径的方法"
category: "windows"
tags: ["windows"]
---

### 前言
很多用户们在使用Win10的时候，都会让一下程序开机自动启动，这样会很方便，但是很多的用户们不清楚Win10开机启动文件夹路径是什么。接下来小编为大家带来Win10系统开机启动文件夹路径的方法，有需要的朋友快来看看吧!

### 操作示例
快捷命令：按下【win+R】打开运行输入：
```
系统自动启动的的目录： shell:Common Startup
用户自动启动的的目录： shell:Startup
```

### 以下是一个启动软件并全屏的`.bat`代码
```
@echo off
rem 防止中文乱码
chcp 65001 > nul

echo 正在打开软件...
echo.
cd /d "E:\Program Files\obs-studio\bin\64bit\"

echo 正在启动软件...
start /MAX "" "obs64.exe"

echo 启动软件中...
rem 等待一段时间，确保软件完全加载
ping 127.0.0.1 -n 10 > nul

echo 开启全屏显示
rem 使用 PowerShell 模拟按下 F11 键
powershell -Command "[System.Reflection.Assembly]::LoadWithPartialName('System.Windows.Forms'); [System.Windows.Forms.SendKeys]::SendWait('{F11}')"

echo
exit
```
