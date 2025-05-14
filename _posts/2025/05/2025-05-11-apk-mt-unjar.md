---
layout: post
title: "查看安卓源码"
keywords: ["apk", "decompile", "mt manager", "cfr"]
description: "使用MT管理器和CFR工具将APK反编译为Java源码"
category: "apk"
tags: ["apk", "decompile", "mt manager", "cfr"]
---

# 使用MT管理器和CFR工具将APK反编译为Java源码

在Android开发或逆向工程中，有时我们需要查看APK文件的源代码。本文将介绍如何使用MT管理器和CFR工具将APK文件反编译为可读的Java源代码。

## 准备工作

1. 安装MT管理器
2. 安装Java运行环境(JRE)
3. 下载CFR工具 (https://github.com/leibnitz27/cfr)

## 步骤一：使用MT管理器提取JAR文件

1. 打开MT管理器
2. 找到并打开目标APK文件
3. 在APK内容中找到`classes.dex`文件
4. 长按选中需要导出的文件夹
5. 选择"批量操作"功能
6. 选择"导出为JAR"选项
7. 选择保存位置并确认导出

## 步骤二：使用CFR工具反编译JAR文件

CFR是一个强大的Java反编译器，可以将JAR文件转换为可读的Java源代码。

1. 打开命令行终端
2. 进入CFR工具所在目录
3. 执行以下命令：

```bash
java -jar cfr-0.152.jar 你的jar文件.jar --outputdir 输出目录
```

例如：
```bash
java -jar cfr-0.152.jar base-enjarify.jar --outputdir F:/decompiled_code
```

注意事项：
- 反编译过程可能需要一些时间，请耐心等待
- 确保有足够的磁盘空间
- 建议使用最新版本的CFR工具以获得更好的反编译效果

## 查看反编译结果

反编译完成后，你可以在指定的输出目录中找到生成的Java源代码文件。这些文件可以直接用任何文本编辑器或IDE打开查看。

## 常见问题

1. 如果遇到Java环境相关错误，请确保正确安装了JRE并设置了环境变量
2. 如果反编译过程中断，可以尝试使用较小的JAR文件或增加JVM内存
3. 某些混淆过的代码可能无法完全还原，这是正常现象

## 总结

通过MT管理器和CFR工具的组合使用，我们可以方便地将APK文件转换为可读的Java源代码。这对于学习Android开发、代码审查或逆向工程都很有帮助。

## 参考资源

- [CFR GitHub仓库](https://github.com/leibnitz27/cfr)
- [MT管理器官网](https://mt2.cn/)
