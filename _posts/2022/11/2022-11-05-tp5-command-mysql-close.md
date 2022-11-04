---
layout: post
title: "ThinkPHP 中使用 PHP pcntl_fork 实现多进程的坑"
keywords: ["php"]
description: "ThinkPHP 中使用 PHP pcntl_fork 实现多进程的坑"
category: "php"
tags: ["php"]
---
## 前言
最近要实现一个功能：根据卡号去第三方平台获取卡的详细信息，但是由于第三方相应速度比较慢(一张卡大概3S)，在批量操作的时候，响应就会很慢，所以想到使用多进程去操作

**后端代码如下**

```php
<?php
namespace app\api\controller;

use \think\Console;
/**
 * 定时脚本执行
 * 为什么要单独写到这个统一的接口（tp中，不会关数据库），参考：https://blog.csdn.net/lovelessdream/article/details/87456227
 *
 * @Author 
 * @DateTime 2022-10-29
 */
class Cron {
    
    public function index() {
        $task = isset($_REQUEST['task']) ? $_REQUEST['task'] : [];
        $par  = isset($_REQUEST['par']) ? $_REQUEST['par'] : [];
        ob_end_flush();
        ob_start();
        set_time_limit(0);
        ignore_user_abort(true);
        header("HTTP/1.1 200 OK");
        header("Content-Length: 0");
        echo str_repeat(" ", 4096*1024);
        ob_flush();
        flush();
        sleep(5);

        //限制白名单
        if(!in_array($task, ['tuiguangStatistics', 'adsTask'])){
            die('access denied');
        }
        // 调用tp中用命令行
        $output = Console::call($task , $par);
    }
}
```

**前端代码如下**

```html
<script>
function cron(ee, task, ...args){
		$(ee).attr('disabled', true);
		$(ee).find('.fa-refresh').addClass('fa-pulse');
		$.ajax({
			type: "POST",
			url: "{:url('/api/cron/index')}",
			data:{ 'task':task, 'par':args} ,
			dataType: "text",
			success: function(data){
				openBtn(ee);
				toastr.success('同步数据成功,预计需要2分钟后才能同步完!');
				setTimeout(function(){
					window.location.reload();
				}, 120000);
			},
			error : function(e){
				openBtn(ee)
				toastr.success('同步数据成功,预计需要2分钟后才能同步完!', e);
			}
		})
	}
	function openBtn(ee){
		setTimeout(function(){
			$(ee).attr('disabled', false);
			$(ee).find('.fa-refresh').removeClass('fa-pulse');
			window.location.reload();
		}, 1000*120)
	}
</script>
```
