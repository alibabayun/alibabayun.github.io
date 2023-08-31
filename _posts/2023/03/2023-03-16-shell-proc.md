---
layout: post
title: "快速将进程启动与停止"
keywords: ["shell"]
description: "快速将进程启动与停止"
category: "shell"
tags: ["shell"]
---

记录下脚本，方便后续使用
```
#!/bin/bash

function shop_proc(){
        # 查找进程ID
        pid=$(ps -ef | grep $1 | grep -v grep | awk '{print $2}')

        # 杀死进程
        if [ -n "$pid" ]; then
          kill -9 $pid
          echo "[$1]进程已停止 PID=[$pid]"
          return 0
        else
          echo "[$1]进程未运行"
          return 0
        fi
}

# 根据参数执行相应的操作
case $1 in
  start)
    cd /opt/lcv2/iot_manager_service/ && ./iot_svr &
    cd /opt/lcv2/ipolesvr  && ./ipolesvr_v2 &
    cd /opt/lcv2/mediasvr  && ./mediasvr_v2 &
    cd /opt/lcv2/websvr  && ./websvr_v2 &
    ;;
  stop)
    shop_proc "iot_svr"
    shop_proc "ipolesvr_v2"
    shop_proc "mediasvr_v2"
    shop_proc "websvr_v2"
    ;;
  *)
    echo "Usage: $0 {start|stop}"
    exit 1
esac

exit 0

```
