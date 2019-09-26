---
layout: post
title: "mysql 批量在某个字段前追加字符"
keywords: ["mysql 批量在某个字段前追加字符#号"]
description: "mysql 批量在某个字段前追加字符"
category: "mysql"
tags: ["mysql", "小技能"]
---
{% include JB/setup %}

### 完整sql

```
update 
	xs_book 
set 
	book_sign=replace(book_sign, book_sign, concat('vip ', book_sign)) 
where 
	book_sign not like 'vip%' and id > 90000
````

### 函数解释
- replace

```
update 表 set 字段=replace(字段,'原始串','替换串')
```

- concat 字符拼接

```
update 表 set 字段=concat(追加字符, 字段)
```

##### 效果如下 在该字段前 追加了 `vip`
![效果图](https://img.alicdn.com/imgextra/i4/1819728314/TB2vkNEsXXXXXXnXpXXXXXXXXXX_!!1819728314.jpg)

