---
layout: post
title: "linux下进程保活脚本"
keywords: ["linux"]
description: "linux下进程保活脚本"
category: "linux"
tags: ["linux"]
---

## 功能说明
定时检测进程启动情况，如果进程掉了重新启动，放在定时任务下可以定时检查任务

```
PATH=/bin:/sbin:/usr/bin:/usr/sbin:/usr/local/bin:/usr/local/sbin:~/bin
export PATH
function chekck(){
    ctime=`date "+%Y-%m-%d %H:%M:%S"`
    process_Key=$1
    pids=`ps -ef | grep "${process_Key}" | grep -v grep | awk '{print $2}'`
    if [ "${pids}" = "" ] #查找的进程未启动
    then
        cd /www/wwwroot/novel_server
         /www/server/php/71/bin/php think "${process_Key}"
        echo "START ${process_Key} SUCCESS"
    else
        echo " ${process_Key} PROCESSOR IS RUNNING"
    fi
  
}

NAME[0]="kpi_server"
NAME[1]="n_server"

for (( i = 0; i < 1; i++ ));
do
    chekck ${NAME[i]}
done
```