---
layout: post
title: "android-studio环境搭建"
keywords: ["android-studio"]
description: "android-studio环境搭建"
category: "android-studio"
tags: ["android-studio"]
---

## 安卓环境搭建常见问题汇总
### 1. Gradle: 下载 fastutil-8.4.0.jar... 好慢
> 如果你在使用 Gradle 下载 fastutil-8.4.0.jar 文件时速度较慢，可以尝试以下解决方法：
更换镜像源：Gradle 使用的默认镜像源可能在某些地区速度较慢。你可以尝试更改 Gradle 的镜像源为一个速度更快的镜像源。在项目根目录下的 build.gradle 文件中添加以下代码：
```
groovyCopy Code
repositories {
    mavenCentral() // 默认镜像源

    // 使用阿里云镜像源（加速国内下载）
    maven { url 'https://maven.aliyun.com/repository/central' }
}
````
然后重新同步 Gradle。
使用本地缓存：如果你之前已经成功下载过 fastutil-8.4.0.jar 文件，Gradle 会将它缓存在本地。你可以查找 Gradle 的本地缓存目录，并将已经下载好的文件拷贝到该目录下。这样 Gradle 在下载时会直接使用本地缓存的文件，而不是重新下载。
使用代理下载：如果你在使用 Gradle 时需要通过代理连接到互联网，确保 Gradle 的代理配置正确。在项目根目录下的 gradle.properties 文件中，添加以下代理配置（根据你的实际代理设置进行修改）：


### 2. gradle不同版本下载太慢 国内镜像
> 参考资料：https://blog.csdn.net/youngwah292/article/details/110734407


### 3. 自带模拟器连不上网
> 直接 使用第三方如雷电模拟器
> 参考资料：https://blog.csdn.net/weixin_43157543/article/details/104589328


### 4. 抓包 Fiddler+雷电模拟器进行APP抓包
> 参考： https://www.cnblogs.com/chenyablog/p/12773990.html


### 5. Android studio中文汉化教程
> 参考： https://blog.csdn.net/weixin_42019349/article/details/135039506

### 6. Android Studio获取SHA1
> 参考：https://blog.csdn.net/sjjsbsbbs/article/details/122887057
