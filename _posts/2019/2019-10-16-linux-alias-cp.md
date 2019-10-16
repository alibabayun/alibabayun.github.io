---
layout: post
title: "linux为防止误操作常用加强命名"
keywords: ["linux"]
description: "linux为防止误操作常用加强命名"
category: "linux"
tags: ["linux"]
---
#### 在系统的`alias`里添加以下命令，让其执行出现提示，二次确认操作

```
# .bashrc
# User specific aliases and functions
alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'
```