---
layout: post
title: "宝塔设置默认php版本"
keywords: ["linux"]
description: "宝塔设置默认php版本"
category: "linux"
tags: ["linux"]
---

## 查看当前软链
```
cd /bin
ls -al | grep php
```

## 将默认php版本设置到5.6
```
ln -sf /www/server/php/56/bin/php /usr/bin/php 
ln -sf  /www/server/php/56/sbin/php-fpm /usr/bin/php-fpm  
ln -sf  /www/server/php/56/bin/phpize /usr/bin/phpize

ln -sf /www/server/php/56/bin/pear /usr/bin/pear
ln -sf /www/server/php/56/bin/pecl /usr/bin/pecl
```