---
layout: post
title: "MAC上使用go ide时，每次重新build之后都会弹出“您要应用程序“main”接受传入网络连接吗？”)"
keywords: ["go"]
description: "MAC上使用go ide时，每次重新build之后都会弹出“您要应用程序“main”接受传入网络连接吗？”)"
category: "go"
tags: ["go"]
---

MAC上使用air时，每次重新build之后都会弹出“您要应用程序“main”接受传入网络连接吗？. ”. 如题，每次 build 之后都会弹出这个框框，是否有办法禁止它弹出呢？.
![](https://cdn.learnku.com/uploads/images/202012/17/74563/i97hsPNzWw.png!large)


已解决，修改以下代码即可： 写一个本地地址

```
http.ListenAndServe("127.0.0.1:3000", nil)
```