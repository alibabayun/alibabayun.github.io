---
layout: post
title: "git-remote 添加多个远程仓库"
keywords: ["git-remote 添加多个远程仓库"]
description: "git-remote 添加多个远程仓库"
category: "git-remote"
tags: ["git-remote"]
---
{% include JB/setup %}

### 使用 `git-remote` 添加多个远程分支的命令


查看远程仓库详情

```
git remote	-v 
````


添加新的仓库地址

```
git remote set-url --add [--push] <name> <newurl>
```

删除仓库地址

```
git remote set-url --delete [--push] <name> <url>
```