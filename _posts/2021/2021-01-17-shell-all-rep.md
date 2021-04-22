---
layout: post
title: "Linux 下批量修改删除文件名中不想要的字段"
keywords: ["linux"]
description: "Linux 下批量修改删除文件名中不想要的字段"
category: "linux"
tags: ["linux"]
---

参考：https://www.cnblogs.com/despotic/p/13565615.html

```
for var in *.sh; do mv "$var" "${var%.sh}.php"; done
```
以下表示将当前目录下所有以sh结尾的换成php