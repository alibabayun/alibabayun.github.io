---
layout: post
title: "git使用注意的地方"
keywords: ["git给我们的坑"]
description: "git给我们的坑"
category: "git"
tags: ["git"]
---
{% include JB/setup %}

### 最近使用git遇到过的坑记录一下

* 文件夹大小写问题
git不是区分大小写的如果你重命名了一个文件夹，不能直接改某个单词了，只能删除再新建一个（当然删之前要记得bak一下）
```
fronts <=====> Fronts
```

*  不能提交｀git add` 空的文件夹
如果你新建了一个文件夹 例如 `upload` 那么此文件夹下必须要有一个文件否则 git add 是不能加到缓存区的

* `create ‘/.git/index.lock’: File exists` 出现此问题时
删除此文件即可





