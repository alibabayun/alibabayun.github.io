---
layout: post
title: "前端项目，忽略es6语法"
keywords: ["js"]
description: "前端项目，忽略es6语法"
category: "js"
tags: ["js"]
---
## 前言
最近在项目中，有些老版本的项目代码，报一些语言错误，为统一处理，现目录下添加`.eslintrc.js`文件，来忽略错误 

## 参考资料
![](https://blog.csdn.net/weixin_43222302/article/details/104760922)

## 代码如下
```js
module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/essential',
    'eslint:recommended'
  ],
  "globals": {
      "AMap": "true",
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'error', //old process.env.NODE_ENV === 'production' ? 'error' : 'off'
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'error' //old process.env.NODE_ENV === 'production' ? 'error' : 'off'
  },
  parserOptions: {
    parser: 'babel-eslint'
  }
}
```