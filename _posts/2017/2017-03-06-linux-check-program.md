---
layout: post
title: "linux下检查程序是否在执行中"
keywords: ["linux"]
description: "linux下检查程序是否在执行中"
category: "linux"
tags: ["linux"]
---
{% include JB/setup %}

检查一个swoole进程是否正常

```
#  program : 判断进行是否存在，并重新启动


function check(){
    count=`ps -ef |grep $1 |grep -v "grep" |wc -l`

    if [ 0 == $count ];then
        echo 'restart' >> /data/logs/swoole_send_msg_check.log
        /usr/local/bin/php /data/www/xiaoshuo/Application/Crontab/xiaomo.php SwooleServer/send_msg >> /data/logs/swoole_send_msg_check.log 2>&1
    else
        echo 'exits' >> /data/logs/swoole_send_msg_check.log
    fi
}

check SwooleServer/send_msg

```

检查 memcached 是否正常

```
#! /bin/bash
#  program : 判断进行是否存在，并重新启动

function check(){
    count=`ps -ef |grep $1 |grep -v "grep" |wc -l`

    if [ 0 == $count ];then
        echo 'restart' >> /data/logs/memcached_check.log
        nohup /usr/local/bin/memcached -d -m 512 -p 11211 -u root -c 40960 -P /var/run/memcached.pid -v >> /data/logs/memcac
hed.log 2>&1
    else
        echo 'exits' >> /data/logs/memcached_check.log
    fi
}

check memcached
````