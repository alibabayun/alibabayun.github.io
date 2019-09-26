---
layout: post
title: " git 撤销commit"
keywords: [" git 撤销commit"]
description: " git 撤销commit"
category: "git"
tags: ["git"]
---

- 如果不小心commit了一个不需要commit的文件，可以对其进行撤销。
- 先使用git log 查看 commit日志

```
commit 422bc088a7d6c5429f1d0760d008d86c505f4abe  
Author: alibabayun <alibabayun@gmail.com>  
Date:   Tue Sep 4 18:19:23 2012 +0800  
  
    删除最近搜索数目限制  
  
commit 8da0fd772c3acabd6e21e85287bdcfcfe8e74c85  
Merge: 461ac36 0283074  
Author: alibabayun <alibabayun@gmail.com>  
Date:   Tue Sep 4 18:16:09 2012 +0800  
```

- 找到需要回退的那次commit的 哈希值，
 
```
git reset --hard commit_id 
```
#### 使用上面的命令进行回退
- 起因: 不小新把记录了公司服务器IP,账号,密码的文件提交到了git

方法: 

```
git reset --hard <commit_id>
git push origin HEAD --force
```


#### 其他:

```
根据–soft –mixed –hard，会对working tree和index和HEAD进行重置:
git reset –mixed：此为默认方式，不带任何参数的git reset，即时这种方式，它回退到某个版本，只保留源码，回退commit和index信息
git reset –soft：回退到某个版本，只回退了commit的信息，不会恢复到index file一级。如果还要提交，直接commit即可
git reset –hard：彻底回退到某个版本，本地的源码也会变为上一个版本的内容

HEAD 最近一个提交
HEAD^ 上一次
<commit_id>  每次commit的SHA1值. 可以用git log 看到,也可以在页面上commit标签页里找到
```