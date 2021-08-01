---
layout: post
title: "java学习笔记"
keywords: ["java"]
description: "java学习笔记"
category: "java"
tags: ["java"]
---

### [234集- `this` 调重载构造器 ](https://www.bilibili.com/video/BV1Kb411W75N?p=234)

- 只能在同一个类中最多只能调用`n-1`次
- 只能放首行如下
  ```
  public Persion(){}
  public Persion(int age){
  	this();
  }
  ```

### [235集-Eclipse快捷键](https://www.bilibili.com/video/BV1Kb411W75N?p=235)
- 将类中常用的 `属性` 快速生成`getName`和`setName`方法
- 将类中常用的 `构造器` 快速生成 空参或多参 的`构造器`
> - mac `alt+command+s`
  - win `alt+shift+s`