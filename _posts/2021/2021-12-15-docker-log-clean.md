---
layout: post
title: "docker下清理docker日志"
keywords: ["docker"]
description: "docker "
category: "docker"
tags: ["docker"]
---

java脚本如果开启了日志显示，可能会产生大量的日志记录，以下脚本可以定时清理日志


以下为sh脚本
```
#!/bin/sh  
time=$(date "+%Y-%m-%d %H:%M:%S")
echo "---------------------------------------------------------------------------"
echo "----- start clean docker containers logs on ${time} ----"  
  
logs=$(find /data/docker/containers -name '*-json.log' )  
for log in $logs  
        do  
                du -h  $log
                cat /dev/null > $log  
        done  
 
echo "--- end clean docker containers logs on ${time}   -----"
```