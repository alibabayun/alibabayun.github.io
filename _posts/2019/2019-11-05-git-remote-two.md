---
layout: post
title: "git对不同的仓库，推送指定固定的ssh-key密钥"
keywords: ["git"]
description: "git对不同的仓库，推送指定固定的ssh-key密钥"
category: "git"
tags: ["git"]
---

### 背景
有时会有这么一个需求，公司给每一个员工只分配了一个密钥，但有时可能在家里或是多台电脑也要推送到仓库。

### 解决方式
1、直接把 `~/.ssh/id_rsa` 和 `~/.ssh/id_rsa.pub` 替换(不建议)
2、设置`vim ~/.ssh/config` 指定不同 host，使用不同的密钥，配置如下
```
# git仓库地址
Host github.com
# git 用户
User git
# git host别名，可以直接用域名，或自己定义
Hostname github.com
# git 端口
port 22
# 指定端口
IdentityFile ~/.ssh/id_rsa_test
```
