---
layout: post
title: "openwrt磁盘变成只读Read-only file system"
keywords: ["openwrt"]
description: "openwrt磁盘变成只读Read-only file system"
category: "openwrt"
tags: ["openwrt"]
---

## 前言
搭建openwrt的过程中，由于机器没有关机键，而且没有进行后台命令关机，直接拔电源之后，重启，任何操作都提示只读状态，那么这个时候什么都操作不了，无奈只有重新做系统，但是问题过于频繁，所以还是花时间解决

## 解决方案
1. 检查文件系统是否已经以只读模式挂载。可以通过运行以下命令检查文件系统的状态：
```
mount | grep " / "
```
2. 磁盘检查
```
e2fsck -y /dev/mmcblk0p14
```

## 参考资料
https://blog.csdn.net/qq_19657437/article/details/118220066
