---
layout: post
title: "php访问文件，不可写"
keywords: ["php访问文件，不可写"]
description: "php访问文件，不可写"
category: "list"
tags: ["list"]
---

```
<?php  
    if(is_writeable($file_name))  
    {  
        //....  
    }  
?>  
```

这里file_name已经设置权限为777.但是还是不可写，查资料，问题为SELinux配置的问题，将SELinux关闭后问题解决。
SELinux关闭办法为：

* 修改`/etc/selinux/config`文件中的`SELINUX=""` 为` disabled `
* 执行命令：`setenforce 0`