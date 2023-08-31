---
layout: post
title: "Windows进行远程桌面连接后如何彻底删除远程记录"
keywords: ["windows"]
description: "Windows进行远程桌面连接后如何彻底删除远程记录"
category: "windows"
tags: ["windows"]
---

## 前言
我们经常会用到Windows系统远程桌面连接这个功能，由于它是临时性的，所以希望在用完之后，能够把自己的远程桌面连接记录彻底删除掉，以免被有心之人利用。删除记录的方法主要分三步走。

## 删除远程IP地址
1. 使用快捷键"win+r"，输入regedit，点击确定，打开注册表。
2. 展开"计算机\HKEY_CURRENT_USER\Software\Microsoft\Terminal Server Client\Default"，你会看到你的远程桌面连接记录(通过IP地址判断)，选中右键删除即可。搞定了这一步，还没有结束，所以请不要把注册表这个界面这么快关掉，因为后面的步骤还会需要用到！

————————————————
版权声明：本文为CSDN博主「飞机火车巴雷特」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_36158230/article/details/121912972
