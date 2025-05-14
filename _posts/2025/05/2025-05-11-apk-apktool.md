---
layout: post
title: "Apktool工具"
keywords: ["apk"]
description: "Apktool工具"
category: "apk"
tags: ["apk"]
---

# Apktool 使用指南

## 1. Apktool 简介

Apktool 是一个用于 Android APK 文件逆向工程的工具，主要功能包括：

- 反编译 APK 文件，提取资源文件
- 查看和修改 smali 代码
- 重新打包 APK 文件
- 支持对 smali 代码进行逐步调试
- 提供类似项目的文件结构

## 2. Windows 安装步骤

### 2.1 下载必要文件
- 下载 Apktool.jar：https://bitbucket.org/iBotPeaches/apktool/downloads/
- 下载 apktool.bat 脚本（如果链接无法访问，可手动创建）

### 2.2 手动创建 apktool.bat
创建一个新的文本文件，将以下内容复制进去，并保存为 apktool.bat：

```batch
@echo off
if "%PATH_TO_FX%" == "" (
  set PATH_TO_FX=%~dp0
)
set PATH_TO_FX=%PATH_TO_FX:;=;%
set PATH_TO_FX=%PATH_TO_FX:""=%
set PATH_TO_FX=%PATH_TO_FX:""=%
set PATH_TO_FX=%PATH_TO_FX:""=%
set PATH_TO_FX=%PATH_TO_FX:""=%
set PATH_TO_FX=%PATH_TO_FX:""=%
set PATHEXT=%PATHEXT:;.JAR;=%
java -jar "%~dp0apktool.jar" %*
```

### 2.3 文件放置与环境变量配置

- 新建文件夹（如 `C:\apktool`），将 `apktool.jar` 和 `apktool.bat` 都放入该文件夹。
- 配置环境变量：
  右键"此电脑" → 属性 → 高级系统设置 → 环境变量 → 系统变量 Path → 编辑 → 新建，添加 `C:\apktool`，保存。

### 2.4 验证安装

打开命令提示符（CMD）或 PowerShell，输入：

```bash
apktool --version
```
若显示版本号，则安装成功。

---

## 3. Apktool 基本用法

### 3.1 反编译 APK

```bash
apktool d 你的应用.apk -o 输出目录
```

### 3.2 重新打包 APK

```bash
apktool b 输入目录 -o 新应用.apk
```

### 3.3 常用参数

- `-f` 强制覆盖已存在目录
- `-r` 只反编译资源文件
- `-s` 只反编译代码

---

## 4. 测试用小型 APK 推荐

### 4.1 Hello World APK

- [HelloWorld.apk 下载地址](https://github.com/ashishb/android-sample-apps/raw/master/HelloWorld/HelloWorld.apk)

### 4.2 F-Droid 开源小应用

- F-Droid 官网：[https://f-droid.org/zh_Hans/packages/](https://f-droid.org/zh_Hans/packages/)

### 4.3 其他开源 APK 集合

- [https://github.com/ashishb/android-sample-apps](https://github.com/ashishb/android-sample-apps)

---

## 5. 注意事项

- 反编译后的代码为 smali 格式，需一定学习成本
- 修改后重新打包的 APK 需重新签名才能安装
- 请遵守相关法律法规，勿用于非法用途
- 某些 APK 可能有混淆或加密，分析难度较大

---

## 6. 参考链接

- [Apktool 官方 GitHub](https://github.com/iBotPeaches/Apktool)
- [apktool.bat 脚本源码](https://github.com/iBotPeaches/Apktool/blob/master/scripts/windows/apktool.bat)

---

如有更多问题，欢迎随时提问！
