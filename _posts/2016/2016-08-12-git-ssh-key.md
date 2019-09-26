---
layout: post
title: "针对不同主机使用不同 SSH Key"
keywords: ["针对不同主机使用不同 SSH Key"]
description: "针对不同主机使用不同 SSH Key"
category: "SSH-Key"
tags: ["SSH-Key"]
---
{% include JB/setup %}

考虑到安全性和便捷性，相信大部分同学都已经习惯了 SSH key 登录这种方式。有时候我们需要针对不同主机使用不同的 key，甚至针对同一个主机使用不同的 key，都可以通过 `~/.ssh/config` 这个配置文件来实现。
默认情况下，ssh 会使用` ~/.ssh/id_rsa`。这里，我通过 ssh-keygen 命令生成另外一个 key 用于 `git.oschina.net` gitlab 服务：

```
cd ~/.ssh/
ssh-keygen -t rsa -C "quguangyu@gmail.com"

Generating public/private rsa key pair.
Enter file in which to save the key (/Users/QuQu/.ssh/id_rsa): id_rsa_gitlab
```

接下来将 id_rsa_gitlab.pub 这个公钥文件内容添加到 gitlab 的后台（Mac 下可以使用 pbcopy 这个命令复制内容到剪切板，避免出现格式问题）。

```
pbcopy < id_rsa_gitlab.pub
```

现在我们来 git clone 项目试试：

```
git clone git@git.oschina.net:qgy18/ququblog2.git

Cloning into 'ququblog2'...
Permission denied (publickey).
fatal: Could not read from remote repository.
```

显然，提示没有权限。因为默认 ssh 根本不认我刚刚生成的 id_rsa_gitlab 这个私钥。我们需要做的是告诉 ssh 要用另外的 key 登录，打开 ~/.ssh/config（没有就新建一个），输入以下内容：

```
#gitlab@ququ
Host git.ququ
  HostName git.oschina.net
  Port 22
  User git
  IdentityFile ~/.ssh/id_rsa_gitlab
```

第一行是注释，第二行是指定如果 Host 匹配上了 git.ququ，就使用接下来几行指定的配置登录 ssh。HostName、Port、User、IdentityFile 分别是具体的主机、端口、用户名和私钥 key 的配置。需要注意的是 Host 可以跟 HostName 一样，也可以定义为你想要的任何内容，所以通常我用一个好记的短名称作为 Host。

将之前的「git@git.oschina.net」替换为「git.ququ」再来试试：

```
git clone git.ququ:qgy18/ququblog2.git

Cloning into 'ququblog2'...
remote: Counting objects: 1360, done.
```

嗯，这样就没问题了。同样，如果要给同一个主机指定不同的 key 文件也很简单：

```
Host host1
  HostName www.xxx.com
  User xx
  IdentityFile ~/.ssh/id_rsa_1

Host host2
  HostName www.xxx.com
  User xx
  IdentityFile ~/.ssh/id_rsa_2
```

这样全局任何地方通过 host1、host2 登录 ssh 时，都会自动选择不同的 key 文件。

所以，通过 ssh 的 config 文件可以进一步简化登录过程。实际上我可以通过「ssh q」登录我的 VPS；配置 SFTP 等服务时，也只用在 host 那一栏填一个「q」，用户名、端口什么的都不用填。因为我有这样的配置：

```
Host q
  HostName www.imququ.com
  Port 22
  User jerry
  IdentityFile ~/.ssh/id_rsa
```

由于参数是集中配置的，如果某天我要更换 ssh 服务的端口，只需要在这里改一次就可以了，十分方便。实际上，ssh config 的 Host 字段还支持通配符，有更高级的玩法，不过我暂时没这复杂的需求。这里有一份[完整文档](http://man.openbsd.org/OpenBSD-current/man5/ssh_config.5)，以后有需要再研究。