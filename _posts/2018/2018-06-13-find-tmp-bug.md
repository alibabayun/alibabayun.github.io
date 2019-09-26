---
layout: post
title: "tmp目录下异常文件查找"
keywords: ["linux"]
description: "tmp目录下异常文件查找"
category: "linux"
tags: ["linux"]
---


###tmp目录下异常文件查找：

查出1天以内有变更的文件
```
find /tmp -mtime -1 | grep -v sess | grep -v sugengxiaoshuo
```