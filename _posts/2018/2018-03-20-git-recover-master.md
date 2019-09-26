---
layout: post
title: "git紧急还原到之前版本"
keywords: ["git"]
description: "git紧急还原到之前版本"
category: "git"
tags: ["git"]
---
{% include JB/setup %}

### 操作步骤大致如下：
```
git reset --hard 版本id
git push  -f

#在线上执行
git reset --hard origin/master
```

### 其它回滚请参考
http://blog.csdn.net/zc474235918/article/details/60136724?locationNum=11&fps=1