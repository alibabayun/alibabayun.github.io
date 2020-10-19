---
layout: post
title: "Android Studio 手动配置Gradle"
keywords: ["Android"]
description: "Android Studio 手动配置Gradle"
category: "Android"
tags: ["Android"]

< 以AS4.0.1对应gradle-6.1.1-all为例 >

Mac下 .Gradle 默认位置：/Users/username/.gradle

终端输入：open .gradle 进入目录

进入文件夹 wrapper/dists/gradle-6.1.1-all/ ①
①：无序的字母和数字文件夹（每个gradle版本下都会有）

将下载好的gradle-6.1.1-all.zip复制进①目录下并解压

重启AS: file- Invalidate Caches / Restart

梯子解决一切：POM、SSL peer shut down incorrectly、依赖下载

作者：齐明晰
链接：https://www.jianshu.com/p/b3e0126c443d
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


阿里镜像
https://maven.aliyun.com/mvn/guide