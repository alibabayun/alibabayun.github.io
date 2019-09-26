---
layout: post
title: "记录一个统计在线时长的js"
keywords: ["js"]
description: "记录一个统计在线时长的js"
category: "linux"
tags: ["js"]
---
{% include JB/setup %}

有时运营这边有会有这样的需求，统计用户的在线时长，最近在美团的一个链接里，
发现此代码，记录一下这个js

```
    // 上报浏览时间, fix quit issues
    setTimeout(function () {
        setTimeout(function () {
            MGE ('duration5', 'durationdeps');
            setTimeout(function () {
                MGE ('duration10', 'durationdeps');
                setTimeout(function () {
                    MGE ('duration15', 'durationdeps');
                    setTimeout(function () {
                        MGE ('duration20', 'durationdeps');
                        setTimeout(function () {
                            MGE ('duration25', 'durationdeps');
                            setTimeout(function () {
                                MGE ('duration30', 'durationdeps');
                            }, 5000);
                        }, 5000);
                    }, 5000);
                }, 5000);
            }, 5000);
        }, 5000);
    }, 0);
```