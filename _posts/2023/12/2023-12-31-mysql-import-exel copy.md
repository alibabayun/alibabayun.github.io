---
layout: post
title: "mysql导入excel空格问题"
keywords: ["mysql"]
description: "mysql导入excel空格问题"
category: "mysql"
tags: ["mysql"]
---

## msyql导入excel空格问题
### 去除左右空格方式一：
```
UPDATE `all` SET `iccid` = TRIM(`iccid`), `access_number` = TRIM(`access_number`);

```

### 方式二 解决mysql去掉字段空格：中间空格，左侧空格，右侧空格，两端空格，水平制表符(tab键或者\t)空格，换行键(\n)空格，回车键(Enter键)空格
```
update
			`all`
set
    iccid = replace(
        replace(replace(iccid, char(9), ''), char(10), ''),
        char(13),
        ''
    ),access_number = replace(
        replace(replace(access_number, char(9), ''), char(10), ''),
        char(13),
        ''
    )
		;


update
			`iccid`
set
    iccid = replace(
        replace(replace(iccid, char(9), ''), char(10), ''),
        char(13),
        ''
    );

```


### 参考资料
https://blog.csdn.net/lvoelife/article/details/133674285#
