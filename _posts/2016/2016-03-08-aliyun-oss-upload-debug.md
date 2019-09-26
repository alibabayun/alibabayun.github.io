---
layout: post
title: "阿里OSS上传object注意的问题"
keywords: ["分享缩略图不显示", "使用oss图片分享图片不显示", "微信"]
description: "为什么使用阿里OSS的图片网址微信分享不显示图片？"
category: "技术分享"
tags: ["bug", "upload"]
---
{% include JB/setup %}

* 最近在一个项目中发现如下问题
`使用阿里OSS的图片网址微信分享时不会显示`


* 找出网址是这样的

```php
$url = 'http://qr.static.xinyuemin.com/headimg/20160307100652_20160227172018_HF1LTP{OFNNG26WZ@HNW`IY.jpg';
```


* 以为是图片问题所以复制链接尝试在网址中打开试试，发现图片变成了

```php
$url2 =  'http://qr.static.xinyuemin.com/headimg/20160307100652_20160227172018_HF1LTP%7BOFNNG26WZ@HNW%60IY.jpg';
```

* 尝试用第二个网址去分享，哈哈，可以了，对比之下原来区别是后部分图片名称是做过`urlencode`了,所以大概知道问题出在哪里了


* 在上传的接口中 阿里去oss Sdk 中的返回obejct 图片名称有时会出现如上的20160307100652_20160227172018_HF1LTP`{`OFNNG26WZ@HNW`/``IY.jpg特殊字条所以必需要处理一下
