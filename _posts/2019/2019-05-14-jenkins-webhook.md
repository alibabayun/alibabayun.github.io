---
layout: post
title: "Jenkins 触发远程构建功能使用"
keywords: ["Jenkins"]
description: "Jenkins 触发远程构建功能使用"
category: "Jenkins"
tags: ["Jenkins"]
---

在工作中我们会有直接想通过http方式运行Jenkins job的需求，这样就不需要每次都进入JENKINS的页面了（比如我本机的话就是 127.0.0.1:8080），其实特别简单，现在就来说说方法。
打开你想要通过http方式运行的Jenkins job，然后找到构建触发器部分，里面有一个选项为“触发远程构建 (例如,使用脚本)”，如图：

![1](https://upload-images.jianshu.io/upload_images/1849270-7a3dc4a77a8089f7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/823/format/webp)


![1](https://upload-images.jianshu.io/upload_images/1849270-52bca1d2a8ed6e0c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)


![1](https://upload-images.jianshu.io/upload_images/1849270-52bca1d2a8ed6e0c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

在输入完身份令牌后，下面会给出提示，就是告诉你怎么去通过http调用这个job：
```
Use the following URL to trigger build remotely: JENKINS_URL/job/PUB_UserSystem_Captcha/build?token=TOKEN_NAME 或者 /buildWithParameters?token=TOKEN_NAME
Optionally append &cause=Cause+Text to provide text that will be included in the recorded build cause. 
```

JENKINS_URL就是你JENKINS页面的地址，如开始说的127.0.0.1:8080

TOKEN_NAME就是刚刚输入完的身份令牌里自己定义的token
这样就可以组成一个url：127.0.0.1:8080/job/PUB_UserSystem_Captcha/build?token=123456
在没有参数的JOB中，在浏览器里面访问这个url，job就自动会开启运行了；

对于有参数的JOB，我们还需要做些修改，那参数名为name的JOB举例，url为：
127.0.0.1:8080/job/PUB_UserSystem_Captcha/buildWithParameters?token=123456&name=Thomas
这个时候有的小伙伴又会问了，用这个url会报403啊！需要登录怎么办？

别着急，按照下面的图示一步一步操作，在最后一个图例中按照里面示范的配置匿名用户的权限就可以啦！

![1](https://upload-images.jianshu.io/upload_images/1849270-400243c2e3974206.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/343/format/webp)

![1](https://upload-images.jianshu.io/upload_images/1849270-d690f37d538c4b0d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

![1](https://upload-images.jianshu.io/upload_images/1849270-a91a917a97767a82.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)


转自
[https://www.jianshu.com/p/aa0dc1157599](https://www.jianshu.com/p/aa0dc1157599)