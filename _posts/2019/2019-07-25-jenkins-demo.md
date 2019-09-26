---
layout: post
title: "jenkins实现远程新部署多台服务器"
keywords: ["jenkins"]
description: "jenkins实现远程新部署多台服务器"
category: "jenkins"
tags: ["jenkins"]
---

## 选择git仓库
### giee仓库插件配置说明
参考：[https://gitee.com/oschina/Gitee-Jenkins-Plugin](https://gitee.com/oschina/Gitee-Jenkins-Plugin)

### gitcoding 仓库说明
参考：[https://blog.csdn.net/zzyzenith/article/details/84343045](https://blog.csdn.net/zzyzenith/article/details/84343045)

## 必需安装插件
```
Localization: Chinese (Simplified)
Gitee 
Coding Webhook
Infrastructure plugin for Publish Over X
Publish Over SSH #多台远程服务器推送
```

## 示例
* 单台示例
![单台示例](http://img.alibabayun.github.io/2019/07/page1.png)
* 多台示例
![多台示例](http://img.alibabayun.github.io/2019/07/page2.png)