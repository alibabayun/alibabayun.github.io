---
layout: post
title: "Windows运行nginx的配置环境"
keywords: ["Windows运行nginx的配置环境"]
description: "Windows运行nginx的配置环境"
category: "Windows"
tags: ["Windows", "环境"]
---
{% include JB/setup %}

#### 环境的配置

* 我们使用的为Windows运行nginx的配置环境，单独去下载PHP，配置Mysql数据库。

* Windows下nginx的配置，下载最新并且适合你机型的nginx的版本，下载后放在你指定的盘下面
* 然后启动，因为nginx默认使用的端口号是80端口，这个端口号是很多的程序默认的使用端口，像我们使用过的apche就是使用这个80端口的
* 你这个时候运行nginx如果没有启动的话，就要检查你的端口是否被其它的程序占用了
* 如果占用了，这个时候你用两个选择，一个是去改ngingx的运行端口，另一个就是我选择的
* 简单直接的把占用的程序干掉，打开界面左下角的开始，点击运行
* 输入“cmd”，确定，在控制面板输入netstat -aon|findstr "80"，这个是时候你会看见那个程序占用了80
* 一般都会是四位数的标识符(pid)代表相应的程序，你看到那个编号，接下来启动任务管理器，在任务管理器的左上方点击“查看->选择列->接着勾上pid”
* 现在你就可以在进程里看到程序运行的标识符（pid）了，你把相应的程序干掉就行了，

*** 接下来是nginx的配置了 *** 

这个的话我们常用的做法是不改他原来本身的配置，我们打开conf文件夹，建立一个vhost的文件夹
找到nginx.conf这个文件，用一个编辑器打开，配置后面包含一下我们刚才新建的文件
    `include vhost/*.conf`

*** 接下在刚刚建立的vhost文件夹下建立一个conf文件 ***

我们把我们要使用的配置文件写在这里的，这样做就能避免把自己的配置文件跟原来的配置文件搞混了，我们需要的配置

```php
    server {
           listen       80;//使用的端口
           server_name  www.test.com; //配置的域名
  
          charset utf-8;
   
          root       D:\www\demo;   //项目文件的所在地址                                                                       
   
         location / {
              if (!-e $request_filename) {
                  rewrite  ^(.*)$  /index.php?s=$1  last;
                  break;
              }
              
             root   D:\www\demo;   //项目文件的所在地址
              index  index.html index.htm index.php;
          }
  
  
         
          location ~ \.php$ {
              fastcgi_pass   127.0.0.1:9000; //本机的地址以及php运行需要的9000端口
              fastcgi_index  index.php;  //默认访问的文件
              include        fastcgi_params; //包含的fastcgi_params文件，因为接下来我们还需要配置
              fastcgi_param  TP_ENV  jm; //项目运行配置的常量及文件
          }
  
      }
```
      
####  打开conf文件夹下面的fastecgi_params文件加入

    `fastcgi_param  SCRIPT_FILENAME    $document_root$fastcgi_script_name;`

###  php的下载与配置

####  下载最新的并适合你电脑的php文件，

   ` php：php-5.6.15-nts-Win32-VC11-x86 `（nginx下php是以FastCGI的方式运行，所以我们下载非线程安全也就是nts的php包）

　　（还会用到）RunHiddenConsole：RunHiddenConsole.zip

2、安装与配置。

　1）php的安装与配置。

　　 直接解压下载好的php包，到D盘wnmp目录（D:\wnmp），这里把解压出来的文件夹重命名成php5。进入文件夹修改php.ini-recommended文件为php.ini，并用Editplus或者Notepad++打开来。找到

    extension_dir = "./ext"
	更改为
	
	extension_dir = "D:/wnmp/php5/ext"
	
	往下看，再找到
	
	;extension=php_mysql.dll
	
	;extension=php_mysqli.dll
	
	前面指定了php的ext路径后，只要把需要的扩展包前面所对应的“;”去掉，就可以了。这里打开php_mysql.dll和php_mysqli.dll，让php支持mysql。
	
	到这里，php已经可以支持mysql了。
	
	　　接下来我们来配置php，让php能够与nginx结合。找到
	
	
	;cgi.fix_pathinfo=1
	我们去掉这里的封号。
	
	cgi.fix_pathinfo=1
	
	
	
	　　nginx+php的环境就初步配置好了，来跑跑看。我们可以输入命令绑定9000端口作为PHP的运行端口 
	
    **D:/wnmp/php5/php-cgi.exe -b 127.0.0.1:9000 -c D:/wnmp/php5/php.ini**
	
	
####你还可以配置一个启动程序，它能帮助你启动nginx和php并后台运行

    来启动php，并手动启动nginx，当然也可以利用脚本来实现。
    首先把下载好的RunHiddenConsole.zip包解压到nginx目录内，RunHiddenConsole.exe的作用是在执行完命令行脚本后可以自动关闭脚本，而从脚本中开启的进程不被关闭。然后来创建脚本，命名为“start_nginx.bat”，我们在Notepad++里来编辑它
	
	复制代码
```
	@echo off
	REM Windows 下无效
	REM set PHP_FCGI_CHILDREN=5
	
	REM 每个进程处理的最大请求数，或设置为 Windows 环境变量
	set PHP_FCGI_MAX_REQUESTS=1000
	 
	echo Starting PHP FastCGI...
	RunHiddenConsole D:/wnmp/php5/php-cgi.exe -b 127.0.0.1:9000 -c D:/wnmp/php5/php.ini
	 
	echo Starting nginx...
	RunHiddenConsole D:/wnmp/nginx/nginx.exe -p D:/wnmp/nginx
	复制代码
	再另外创建一个名为stop_nginx.bat的脚本用来关闭nginx
	
	@echo off
	echo Stopping nginx...  
	taskkill /F /IM nginx.exe > nul
	echo Stopping PHP FastCGI...
	taskkill /F /IM php-cgi.exe > nul
	exit 
```

### 到这里的话就差不多了，因为我数据库的使用的是 Navicatfor Mysql 这个你下载好就可以使用了

	    


	
	