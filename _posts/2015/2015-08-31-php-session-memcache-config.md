---
layout: post
title: "基于php使用memcache存储session的详解"
keywords: ["session"]
description: "基于php使用memcache存储session的详解"
category: "session"
tags: ["php"]
---
{% include JB/setup %}
![images](https://img.alicdn.com/imgextra/i3/1819728314/TB2.DCJeVXXXXavXXXXXXXXXXXX_!!1819728314.jpg)

web服务器的php session都给memcached ，这样你不管分发器把 ip连接分给哪个web服务器都不会有问题了，配置方法很简单，就在php的配置文件内
增加一条语句就可以了，不过前提你需要装好memcache模块

###### 设置session用memcache来存储
方法I: 在 php.ini 中全局设置
```
session.save_handler = memcache
session.save_path = "tcp://127.0.0.1:11211"
```

方法II： 某个目录下的 .htaccess ：
```
php_value session.save_handler "memcache"
php_value session.save_path  "tcp://127.0.0.1:11211"
```

方法III： 再或者在某个一个应用中：
```
ini_set("session.save_handler", "memcache");
ini_set("session.save_path", "tcp://127.0.0.1:11211");
```

使用多个 memcached server 时用逗号","隔开，并且和 Memcache::addServer() 文档中说明的一样，可以带额外的参数"persistent"、"weight"、"timeout"、"retry_interval" 等等，类似这样的："tcp://host1:port1?persistent=1&weight=2,tcp://host2:port2" 。
如果安装的PECL是memcached(使用libmemcache库的那个)，则配置应为
```
ini_set("session.save_handler", "memcached"); // 是memcached不是memcache
ini_set("session.save_path", "127.0.0.1:11211"); // 不要tcp:
```

###### 启动 memcached：
memcached -d -l 127.0.0.1 -p 11212 -m 128


或 启动Memcache的服务器端：

```c
memcached -d -m 100 -u root -l 192.168.36.200 -p 11211 -c 256 -P /tmp/memcached.pid  
# /usr/local/bin/memcached -d -m 10 -u root -l 192.168.0.200 -p 12000 -c 256 -P /tmp/memcached.pid
```

引用
    -d选项是启动一个守护进程，
    -m是分配给Memcache使用的内存数量，单位是MB，我这里是100MB，
    -u是运行Memcache的用户，我这里是root，
    -l是监听的服务器IP地址，如果有多个地址的话，我这里指定了服务器的IP地址192.168.36.200，
    -p是设置Memcache监听的端口，我这里设置了11211，最好是1024以上的端口，我们这里统一使用11211
    -c选项是最大运行的并发连接数，默认是1024，我这里设置了256，按照你服务器的负载量来设定。
    -P是设置保存Memcache的pid文件，我这里是保存在/tmp/memcached.pid，
    
###### 在程序中使用 memcache 来作 session 存储
用例子测试一下：
复制代码 代码如下:

```php
<?php  
    session_start();  
    if (!isset($_SESSION['TEST'])) {
        $_SESSION['TEST'] = time();
    }  
    
    $_SESSION['TEST3'] = time();
    
    print $_SESSION['TEST'];
    print "<br><br>";
    print $_SESSION['TEST3'];
    print "<br><br>";
    print session_id();
?>  
```

###### 用 sessionid 去 memcached 里查询一下：
复制代码 代码如下:
``` 
<?php  
  $memcache = memcache_connect('localhost', 11211);  
  var_dump($memcache->get('19216821213c65cedec65b0883238c278eeb573e077'));  
  $memcache->set('aaaa', 'hello everyone');  
  var_dump($memcache->get('aaaa'));  
?> 
```

会看到
```
string(37) "TEST|i:1177556731;TEST3|i:1177556881;"
```
这样的输出，证明 `session` 正常工作。
用 `memcache` 来存储` session` 在读写速度上会比 files 时快很多，而且在多个服务器需要共用 session 时会比较方便，将这些服务器都配置成使用同一组 memcached 服务器就可以，减少了额外的工作量。缺点是 session 数据都保存在 memory 中，持久化方面有所欠缺，但对 session 数据来说也不是很大的问题。

===================================
一般地， Session 是以文本文件形式存储在服务器端的。如果使用 Seesion，或者该 PHP 文件要调用 Session 变量，那么就必须在调用 Session 之前启动它，使用 session_start() 函数。其它都不需要你设置了，PHP 自动完成 Session 文件的创建。其默认 Session 的存放路径是服务器的系统临时文件夹。 
但是如果碰到大数据量的Sesstion的时候， 使用基于文件的Session存取瓶颈可能都是在磁盘IO操作上，现在利用Memcached来保存Session数据，直接通过内存的方式，效率自然能够提高不少。 在读写速度上会比 files 时快很多，而且在多个服务器需要共用 session 时会比较方便，将这些服务器都配置成使用同一组 memcached 服务器就可以，减少了额外的工作量。

其缺点是 session 数据都保存在 memory 中，一旦宕机，数据将会丢失。但对 session 数据来说并不是严重的问题。
如何用 memcached 来存储 session呢？以下是基本的配置步骤：
1. 安装 memcached 
在 phpinfo 输出中的 “Registered save handlers” 会有 “files user sqlite”。

2. 修改配置文件，
a. 在 php.ini 中全局设置（* 需要重启服务器）
 ```      
session.save_handler = memcache
session.save_path = "tcp://127.0.0.1:11211"
```
b. 或者某个目录下的 .htaccess :
```
php_value session.save_handler "memcache"
php_value session.save_path "tcp://127.0.0.1:11211"
```
c. 也可以在某个一个应用中：
```
ini_set("session.save_handler", "memcache");
ini_set("session.save_path", "tcp://127.0.0.1:11211");
```
注：使用多个 memcached server 时用逗号”,”隔开，并且和 Memcache::addServer() 文档中说明的一样，可以带额外的参数”persistent”、”weight”、”timeout”、”retry_interval” 等等，类似这样的：”tcp://host:port?persistent=1&weight=2,tcp://host2 :port2″ 。

######  启动 memcached
```
memcached -d -m 10 -u root -l 127.0.0.1 -p 11211 -c 256 -P /tmp/memcached.pid
```
###### 测试 创建一个 session
复制代码 代码如下:
```
<?php
    //set_session.php
    session_start();
    if (!isset($_SESSION['admin'])) {
    $_SESSION['TEST'] = 'wan';
    }
    print $_SESSION['admin'];
    print "\n";
    print session_id();
?>
```

###### 用 sessionid 去 memcached 里查询一下
复制代码 代码如下:
```
<?php
    //get_session.php
    $mem = new Memcache;
    $mem->connect("127.0.0.1", 11211);
    var_dump($mem->get('0935216dbc0d721d629f89efb89affa 6'));
?>
```


