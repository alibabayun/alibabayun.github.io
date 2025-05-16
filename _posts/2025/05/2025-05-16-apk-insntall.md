---
layout: post
title: "apk adb 调试"
date: 2025-05-16 10:00:00 +0800
categories: [技术]
tags: [apk]
---

# ADB 常用命令

## 连接设备
```bash
# 通过 IP 地址连接设备
adb connect 192.168.1.41:5555
```

## 查看设备列表
```bash
# 列出所有已连接的设备
adb devices
```

## 安装应用
```bash
# 指定设备安装 APK
adb -s 192.168.1.41:5555 install C:\Users\79374\Documents\leidian\mt_path\远盈龙虎榜_1.0_sign.apk
```

## 卸载应用
```bash
# 指定设备卸载应用
adb -s 192.168.1.41:5555 uninstall net.h5apk.google
```

## 其他常用命令

### 查看应用列表
```bash
# 列出设备上所有应用
adb shell pm list packages

# 列出第三方应用
adb shell pm list packages -3
```

### 查看应用信息
```bash
# 查看应用详细信息
adb shell dumpsys package <package_name>
```

### 清除应用数据
```bash
# 清除应用数据和缓存
adb shell pm clear <package_name>
```

### 启动应用
```bash
# 启动应用
adb shell am start -n <package_name>/<activity_name>
```

### 停止应用
```bash
# 强制停止应用
adb shell am force-stop <package_name>
```

### 查看日志
```bash
# 查看实时日志
adb logcat

# 过滤特定应用的日志
adb logcat | grep <package_name>
```

### 文件操作
```bash
# 将文件推送到设备
adb push <local_file> <remote_path>

# 从设备拉取文件
adb pull <remote_file> <local_path>
```

### 截图
```bash
# 截取屏幕
adb shell screencap -p /sdcard/screenshot.png
adb pull /sdcard/screenshot.png
```

### 录屏
```bash
# 开始录制屏幕
adb shell screenrecord /sdcard/record.mp4

# 停止录制（按 Ctrl+C）
```

