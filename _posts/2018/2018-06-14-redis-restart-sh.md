---
layout: post
title: "Linux下Redis启动/关闭/重启服务脚本"
keywords: ["redis"]
description: "Linux下Redis启动/关闭/重启服务脚本"
category: "redis"
tags: ["redis"]
---

```
#!/bin/bash
#chkconfig: 2345 55 25
#description: Starts,stops and restart the redis-server
#Ver:1.1  
#Write by ND chengh(200808)
#usage: ./script_name -p [port] {start|stop|status|restart}

# Source function library.
. /etc/rc.d/init.d/functions

# Source networking configuration.
. /etc/sysconfig/network

# Check networking is up.
[ "$NETWORKING" = "no" ] && exit 0

RETVAL=0
REDIS_PORT=6379
PID=

if [ "$1" = "-p" ]; then
    REDIS_PORT=$2
    shift 2
fi

REDIS_DIR="/usr/local/redis"
REDIS="${REDIS_DIR}/bin/redis-server"
PROG=$(basename $REDIS)

CONF="${REDIS_DIR}/bin/redis-${REDIS_PORT}.conf"

if [ ! -f $CONF ]; then
   if [ -f "${REDIS_DIR}/bin/redis.conf" ];then
      CONF="${REDIS_DIR}/bin/redis.conf"
   else
      echo -n $"$CONF not exist.";warning;echo
      exit 1
   fi
fi

PID_FILE=`grep "pidfile" ${CONF}|cut -d ' ' -f2`
PID_FILE=${PID_FILE:=/var/run/redis.pid}
LOCKFILE="/var/lock/subsys/redis-${REDIS_PORT}"

if [ ! -x $REDIS ]; then
    echo -n $"$REDIS not exist.";warning;echo
    exit 0
fi


start() {

    echo -n $"Starting $PROG: "
    $REDIS $CONF
    RETVAL=$?
    if [ $RETVAL -eq 0 ]; then
        success;echo;touch $LOCKFILE
    else
        failure;echo
    fi
    return $RETVAL

}

stop() {

    echo -n $"Stopping $PROG: "

    if [ -f $PID_FILE ] ;then
       read PID <  "$PID_FILE" 
    else 
       failure;echo;
       echo -n $"$PID_FILE not found.";failure;echo
       return 1;
    fi

    if checkpid $PID; then
     kill -TERM $PID >/dev/null 2>&1
        RETVAL=$?
        if [ $RETVAL -eq 0 ] ;then
                success;echo 
                echo -n "Waiting for Redis to shutdown .."
         while checkpid $PID;do
                 echo -n "."
                 sleep 1;
                done
                success;echo;rm -f $LOCKFILE
        else 
                failure;echo
        fi
    else
        echo -n $"Redis is dead and $PID_FILE exists.";failure;echo
        RETVAL=7
    fi    
    return $RETVAL

}

restart() {
    stop
    start
}

rhstatus() {
    status -p ${PID_FILE} $PROG
}

hid_status() {
    rhstatus >/dev/null 2>&1
}

case "$1" in
    start)
        hid_status && exit 0
        start
        ;;
    stop)
        rhstatus || exit 0
        stop
        ;;
    restart)
        restart
        ;;
    status)
        rhstatus
        RETVAL=$?
        ;;
    *)
        echo $"Usage: $0 -p [port] {start|stop|status|restart}"
        RETVAL=1
esac

exit $RETVAL
```