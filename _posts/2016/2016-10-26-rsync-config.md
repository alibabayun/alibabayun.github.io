---
layout: post
title: "rsync同步配置"
keywords: ["rsync同步配置"]
description: "rsync同步配置"
category: "rsync"
tags: ["rsync"]
---
{% include JB/setup %}

client 启动命令

```
/usr/bin/rsync --daemon --config=/etc/rsyncd_game.conf
```

### client  `rsyncd_game.conf`配置为

```
uid = root
gid = root
max connections = 3600
read only = no
log file = /var/log/rsyncd.log
pid file = /var/run/rsyncd.pid
lock file = /var/run/rsyncd.lock

[tbgame]
#模块自定义名称 ---这个需要与源服务器Sersync对应
path = /data2/www/project
[barrage]
#系统目录
path = /data/www/barrage

#需要同步的目录
[alisms]
#阿里大鱼短信服务
path = /data/3rd_part/alisms

comment = Mirror to tongbu test
ignore errors
read only = no
auth users = user
secrets file = /etc/rsync.pas
#密码认证文件，必须为600权限，否则rsync传输会报错
#hosts allow = *
#允许访问源服务器IP
#hosts deny = 0.0.0.0/32
#拒绝所有IP连接，先允许后拒绝
```

### client  `/etc/rsync.pas`配置为

```
user:1234
```

### service `/etc/rc.local ` 配置

```
# 添加以下命令
/usr/local/sersync/bin -r -d -o /usr/local/sersync/conf/project_test.xml 
```

编译为如下：
```
cd /root/git_code/test/project && rsync -arltuz -R --delete ./ user@you_ip::tbgame --password-file=/etc/rsync.pas
```

### service `project_test.xml ` 配置

```
<?xml version="1.0" encoding="ISO-8859-1"?>
<head version="2.5">
    <host hostip="localhost" port="8008"></host>
    <debug start="false"/>
    <fileSystem xfs="false"/>
    <filter start="false">
	<exclude expression="(.*)\.svn"></exclude>
	<exclude expression="(.*)\.gz"></exclude>
	<exclude expression="^info/*"></exclude>
	<exclude expression="^static/*"></exclude>
    </filter>
    <inotify>
	<delete start="true"/>
	<createFolder start="true"/>
	<createFile start="false"/>
	<closeWrite start="true"/>
	<moveFrom start="true"/>
	<moveTo start="true"/>
	<attrib start="false"/>
	<modify start="false"/>
    </inotify>

    <sersync>
	<!--<localpath watch="/root/git_code/test/project_name">-->
	   <!-- <remote ip="115.29.237.65" name="tbgame"/>-->
	    <!--<remote ip="192.168.8.39" name="tongbu"/>-->
	    <!--<remote ip="192.168.8.40" name="tongbu"/>-->
	<!--</localpath>-->
	<localpath watch="/root/git_code/produce/project_name">
	    <remote ip="115.29.168.86" name="project_name"/>
	    <remote ip="115.29.251.158" name="project_name"/>
	    <!--<remote ip="192.168.8.40" name="tongbu"/>-->
	</localpath>
	<rsync>
	    <commonParams params="-arltuz"/>
	    <auth start="true" users="user" passwordfile="/etc/rsync.pas"/>
	    <userDefinedPort start="false" port="874"/><!-- port=874 -->
	    <timeout start="false" time="100"/><!-- timeout=100 -->
	    <ssh start="false"/>
	</rsync>
	<failLog path="/tmp/rsync_fail_log.sh" timeToExecute="60"/><!--default every 60mins execute once-->
	<crontab start="false" schedule="600"><!--600mins-->
	    <crontabfilter start="false">
		<exclude expression="*.php"></exclude>
		<exclude expression="info/*"></exclude>
	    </crontabfilter>
	</crontab>
	<plugin start="false" name="command"/>
    </sersync>

    <plugin name="command">
	<param prefix="/bin/sh" suffix="" ignoreError="true"/>	<!--prefix /opt/tongbu/mmm.sh suffix-->
	<filter start="false">
	    <include expression="(.*)\.php"/>
	    <include expression="(.*)\.sh"/>
	</filter>
    </plugin>

    <plugin name="socket">
	<localpath watch="/opt/tongbu">
	    <deshost ip="192.168.138.20" port="8009"/>
	</localpath>
    </plugin>
    <plugin name="refreshCDN">
	<localpath watch="/data0/htdocs/cms.xoyo.com/site/">
	    <cdninfo domainname="ccms.chinacache.com" port="80" username="xxxx" passwd="xxxx"/>
	    <sendurl base="http://pic.xoyo.com/cms"/>
	    <regexurl regex="false" match="cms.xoyo.com/site([/a-zA-Z0-9]*).xoyo.com/images"/>
	</localpath>
    </plugin>
</head>
```

### service  `/etc/rsync.pas`配置为

```
1234
```

最后运行一下

```
/usr/local/sersync/bin -r -d -o /usr/local/sersync/conf/project_test.xml
```