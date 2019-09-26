---
layout: post
title: "opensearch排序"
keywords: ["opensearch"]
description: "opensearch精排与粗排的基本使用"
category: "技术分享"
tags: ["opensearch排序"]
---
{% include JB/setup %}

#### 最近有一个(opensearch)的需求，如下：
*  `end_time`为时间戳，如果当前时间大于`end_time`则表示 `过期`
* 没过期，优先按`sort`排序
* 过期了，则按`end_time` 倒序排序

![图片加载中](https://img.alicdn.com/imgextra/i4/1819728314/TB21HVifpXXXXcBXXXXXXXXXXXX_!!1819728314.jpg)


咨询了下阿里的工程师们
给出了以下的回复：


<pre>
云搜索_客户接入(zhengmay8277) (2015-09-15 12:07:38):
这种可以在排序表达式中使 精排 if(now()>endtime,end_time,sort) 注意下sort和end_time之间的值域范围调整下   另外粗排也需要考虑下

云搜索_客户接入(zhengmay8277) (2015-09-15 13:24:52):
上面是排序表达式  搜索后会按照排序表达式分值来排序并返回  所以需要保证排序表达式的分值是符合期望值得。

云搜索_客户接入(zhengmay8277) (2015-09-15 13:28:20):
这种可以在排序表达式中使 精排 if(now()>endtime,end_time,sort) 注意下sort和end_time之间的值域范围调整下   另外粗排也需要考虑下
那你可以试下这样 粗排timeliness（end_time），精排 if(now()>endtime,timeliness(end_time),sort+1)
</pre>


然后打开管理中心按他们提供资料的配置好，果然可以了！顺便看了下里面的一些排序函数
真心感觉到openSearch的强大：

-----

粗排函数支持
![图片加载中](https://img.alicdn.com/imgextra/i2/1819728314/TB2EMg7fXXXXXbkXpXXXXXXXXXX_!!1819728314.png)

-----

强大的精排函数
![图片加载中](https://img.alicdn.com/imgextra/i3/1819728314/TB2vvJpfpXXXXa7XXXXXXXXXXXX_!!1819728314.jpg)

----

![图片加载中](https://img.alicdn.com/imgextra/i4/1819728314/TB2qf8dfpXXXXXYXpXXXXXXXXXX_!!1819728314.jpg)
