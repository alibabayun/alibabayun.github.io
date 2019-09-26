---
layout: post
title: "docker-compose中运行定时任务"
keywords: ["docker"]
description: "docker-compose中运行定时任务"
category: "docker"
tags: ["docker"]
---

### 前言

最近项目中有一个这样的需求,需要在`docker`中运行`crontab`

### 常见问题及修复
1. 如果我们在`docker-entrypoint`直接把crontab文件映射到
`/var/spool/cron/root`, 这样是不会执行的, 因为修改了后,要重启`crond`才能生效.
```
/usr/bin/systemctl restart crond
```

2. 如果你是在容器中操作的, 那么执行上面这条你可能会遇到这种问题
```
docker Failed to get D-Bus connection 错误
```

3. 如果你是单一docker你可以执行以下语句来修复上面的问题
```
docker run --privileged -ti --name test1  centos /usr/sbin/init
```

4. 如果你是用`docker-compose.yml`来运行的话你只能在`docker-entrypoint`开机执行以下
```
#!/bin/sh
/usr/sbin/crond \
&& /usr/bin/cat /data/www/project/docker/crontab/root >> /var/spool/cron/root \
&& /usr/bin/crontab /var/spool/cron/root \ #重点是这一步很重要
&& /usr/sbin/sshd -D
```

5. 如果你执行了上条`/usr/bin/crontab /var/spool/cron/root`出现了报错,可能是因为你的`/var/spool/cron/root`的编辑格式不对最佳方案如下
```
 crontab -l > new_root
```
这样就不会出现上面的问题


### 最后总结

当我们在操作docker时，一定要在测试环境生成一次，不要把异常带到线上，否则处理的难度是处理的时候增加的不是一两倍了.