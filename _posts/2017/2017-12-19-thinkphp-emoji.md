---
layout: post
title: "㊙️emoji-mysql：不正确的字符串值：\xF0\x9F\x98\xB8列"
keywords: ["emoji-mysql：不正确的字符串值：\xF0\x9F\x98\xB8'列"]
description: "emoji-mysql：不正确的字符串值：\xF0\x9F\x98\xB8列"
category: "emoji"
tags: ["emoji", "emoji"]
---

#### 如果在数据库中要保存emoji表情符号时，除了设置好mysql的一些配置外，还有一个thinkphp里的坑，要特别注意下

### 1、在配置文件中加入
```
    'DB_CHARSET' =>    'utf8mb4', // 数据库的编码 默认为utf8
```

### 2、修改mysql的配置文件，windows下的为my.ini(linux下的为my.cnf)，修改的内容都一样
```
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci
```