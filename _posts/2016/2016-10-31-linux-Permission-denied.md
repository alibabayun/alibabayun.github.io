---
layout: post
title: "使用ssh-key登陆提示Permission denied"
keywords: ["使用ssh-key登陆提示Permission denied"]
description: "使用ssh-key登陆提示Permission denied"
category: "Permission"
tags: ["Permission"]
---
{% include JB/setup %}

ssh-key 或 使用git  key方式时如出现

```
Permission denied (publickey,gssapi-keyex,gssapi-with-mic) on openshift
```

### key登陆:
- `~/.ssh/authorized_keys`
- 要设置为 chmod 600 `~/.ssh/authorized_keys`

### git-key登陆
- `~/.ssh/config`
- 要设置为 chmod 600 `~/.ssh/config`


### 如何以ssh-key的方式登陆服务器

1、 在我的电脑上生成 `ssh-keygen `
得到 私钥、公钥

2、 将我的电脑上的公钥上传到远程服务器
并追加入到 

```
*.pub >> ~/.ssh/authorized_keys
```

3、 在我的电脑上指定 私钥文件 登陆到远程