---
layout: post
title: "sublime代码格式化自动对齐"
keywords: ["sublime"]
description: "sublime代码格式化自动对齐"
category: "sublime"
tags: ["sublime"]
---

### 通过包安装管理器安装phpfmt插件.
- 在sublime界面按快捷键 ctrl+shift+P
- 选择并选择: Install Package
- 等待片刻输入并选择 phpfmt

### 配置说明
配置`Preference->Package Settings->phpfmt->Settings-User`, 添加如下配置
```
{
	"enable_auto_align":true,//自动调整对齐
	"indent_with_space": true,//自动空格，tab不会出现
    "format_on_save": true, //保存时自动格式化
    "php_bin": "/usr/bin/php",
    "version": 1
}
```