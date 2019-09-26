---
layout: post
title: "linux如何解决root用户Operation not permitted"
keywords: ["linux"]
description: "linux如何解决root用户Operation not permitted"
category: "linux"
tags: ["linux"]
---
{% include JB/setup %}

在Linux系统中，拥有最高权限的用户root，在执行文件权限的修改，或者修改文件时也会出现如下错误：

1. chmod: changing permissions of 'xxx': Operation not permitted；

2. E45: 'readonly' option is set (add ! to override)


### 问题修复:
```
去除i属性：chattr -i /etc/sysctl.conf
添加i属性：chattr +i /etc/sysctl.conf
```

参考资料：
[https://jingyan.baidu.com/article/fcb5aff7786e1eedab4a7142.html](https://jingyan.baidu.com/article/fcb5aff7786e1eedab4a7142.html)