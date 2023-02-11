---
layout: post
title: "python项目自动生成环境配置文件requirements.txt"
keywords: ["python"]
description: "python项目自动生成环境配置文件requirements.txt"
category: "python"
tags: ["python"]
---
## 前言
最近python 项目自动生成环境配置文件requirements.txt

## 生成方法
### 方法一：整个环境下的安装包都保存到requirements.txt中
```
pip freeze > requirements.txt
```

### 方法二：只生成单个项目中的使用到的安装包
```
pip install pipreqs
pipreqs .
```

参考：https://blog.csdn.net/pearl8899/article/details/113877334