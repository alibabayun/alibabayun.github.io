---
layout: post
title: "xfs文件系统修复方法mount挂载不了/dev/???"
keywords: ["xfs文件系统修复方法mount挂载不了/dev/???"]
description: "xfs文件系统修复方法mount挂载不了/dev/???"
category: "xfs文件系统修复"
tags: ["linux", "xfs文件系统修复"]
---

#### 首先尝试mount和umount文件系统，以便重放日志，修复文件系统，如果不行，再进行如下操作。

1、检查文件系统：先确保umount
xfs_check /dev/sdd(盘符); echo $? 
返回0表示正常

2、执行xfs_repair -n，检查文件系统是否损坏，如何损坏会列出将要执行的操作
如果幸运的话，会发现没有问题，你可以跳过后续的操作。
该命令将表明会做出什么修改，一般情况下速度很快，即便数据量很大，没理由跳过。


3、执行xfs_repair修复文件系统
xfs_repair /dev/sdd (ext系列工具为fsck)

4、最后方法：损失部分数据的修复方法
根据打印消息，修复失败时：
先执行xfs_repair -L /dev/sdd(清空日志，会丢失文件)，再执行xfs_repair /dev/sdd，再执行xfs_check /dev/sdd 检查文件系统是否修复成功。
说明：-L是修复xfs文件系统的最后手段，慎重选择，它会清空日志，会丢失用户数据和文件。
 
备注：
在执行xfs_repair操作前，最好使用xfs_metadump工具保存元数据，一旦修复失败，最起码可以恢复到修复之前的状态。
xfs_metadump为调试工具，可以不管，跳过。


参考：

http://oss.sgi.com/archives/xfs/2010-06/msg00274.html

http://m.blog.csdn.net/blog/skdkjxy/41648713



本文出自 “敏而好学” 博客，请务必保留此出处http://dangzhiqiang.blog.51cto.com/7961271/1657511

xfs文件系统修复方法