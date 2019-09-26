---
layout: post
title: "利用webhooks自动发布代码"
keywords: ["nginx"]
description: "利用webhooks自动发布代码"
category: "gitosc"
tags: ["gitosc", "webhooks"]
---
{% include JB/setup %}

利用webhooks自动发布代码

#### 什么是webhooks？
webhooks 使用简介
[请移步gitosc查看](http://git.oschina.net/oschina/git-osc/wikis/WebHook-%E4%BD%BF%E7%94%A8%E7%AE%80%E4%BB%8B)


##### 创建自动 pull 代码的 shell脚本 `git_update.sh`
```
#!/bin/sh

WEB_PATH='/data/www/＊＊＊'
WEB_USER='www'
WEB_USERGROUP='www'

echo "Start deployment"
cd $WEB_PATH
echo "pulling source code..."
git reset --hard origin/master
git clean -f
git pull
git checkout master
echo "changing permissions..."
chown -R $WEB_USER:$WEB_USERGROUP $WEB_PATH
echo "Finished."
```

#### 创建webhooks 钩子接口 `update.php`

```
<?php

if(!isset($_REQUEST['hook'])) {
        die('No permissions');
}

$hook = $_REQUEST['hook'];
$hook_obj = json_decode($hook);

if($hook_obj->password !== 'webhooks_password') {
        error_log("[password] is error\n", 3, '/var/log/webhook/git_update.log');
        die('password is error!');
}

error_log("[push] is ok\n", 3, '/var/log/webhook/git_update.log');

// 注意此函数可能会被禁用 请在php.ini中设置
shell_exec("/bin/bash /opt/bin/auto_push_code/git_update.sh >> /var/log/webhook/git_update.log 2>&1");

die('update is ok!');
?>
```

#### 注意权限问题

```
WEB_USER
WEB_USERGROUP
```

设置 `http://**.com/update.php` 到 `webhooks` 中就可以测试

最后就可测试 `webhooks` 接口了，大功告成...



