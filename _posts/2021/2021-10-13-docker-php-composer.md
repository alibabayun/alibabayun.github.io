---
layout: post
title: "php5.6 docker内安装composer"
keywords: ["composer"]
description: "php5.6 docker内安装composer"
category: "composer"
tags: ["composer"]
---

## 下载composer.par
```
wget https://getcomposer.org/composer.phar --no-check-certificate
```

## 设置快捷方式
```
mv composer.phar /usr/local/bin/composer
chmox +x  /usr/local/bin/composer
```

## 设置阿里源
```
composer config -g repo.packagist composer https://mirrors.aliyun.com/composer/
```

## 安装
```
composer install

```