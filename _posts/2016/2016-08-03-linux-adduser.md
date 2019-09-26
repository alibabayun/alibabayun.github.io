---
layout: post
title: "linux下添加用户及用户组和home目录"
keywords: ["linux下添加用户及用户组和home目录"]
description: "linux下添加用户及用户组和home目录"
category: "php-fpm"
tags: ["php-fpm"]
---
{% include JB/setup %}


```
useradd -g alibabayun -d /home/alibabayun -s /bin/bash alibabayun
```

语　　法：

```
useradd [-mMnr][-c <备注>][-d <登入目录>][-e <有效期限>][-f <缓冲天数>][-g <群组>][-G <群组>][-s <shell>][-u <uid>][用户帐号] 或 useradd -D [-b][-e <有效期限>][-f <缓冲天数>][-g <群组>][-G <群组>][-s <shell>] 
```

添加环境变量

```
 PATH=/opt/:$PATH
 . /etc/profile
```