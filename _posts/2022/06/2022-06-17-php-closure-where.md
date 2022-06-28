---
layout: post
title: "PHP怎么获取Closure闭包函数的static、this和parameter属性值"
keywords: ["php"]
description: "PHP怎么获取闭包函数的static、this和parameter属性值"
category: "php"
tags: ["php"]
---

## 背景
最近使用tp5时，用到一个 list($where, $sort, $order, $offset, $limit) = $this->buildparams(); 将参数转成条件的闭包函数， 需要将里面的where条件取出来.

## 解决方案
参考资料：https://blog.csdn.net/qq_23586923/article/details/117807033

## 参考代码
```
 $reflection = new \ReflectionFunction($where);
            $arguments  = $reflection->getStaticVariables();
            var_dump($arguments);exit;
```