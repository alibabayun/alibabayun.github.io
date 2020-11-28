---
layout: post
title: "阿里云CDN a,b,c三种鉴权的PHP代码"
keywords: ["cdn"]
description: "阿里云CDN a,b,c三种鉴权的PHP代码"
category: "cdn"
tags: ["cdn"]
---

- 参考 ：http://www.aliyun123.cn/news/217.html
- 参考2：http://bang.ykit.cn/3740.html


```
<?php
error_reporting(E_ALL & ~E_NOTICE); //过滤脚本提醒
date_default_timezone_set('PRC'); //时区设置 解决某些机器报错

/**
 * 生成鉴权url
 */
function PrivateKeyA($url, $cdn_domain='mp.gstartec.cn'){
	$schme = parse_url($url,PHP_URL_SCHEME);  
	$host  = parse_url($url,PHP_URL_HOST);  
	$host  = str_replace($host, $cdn_domain, $host);

	$time     = strtotime("-1700 second"); //默认是100秒失效
	// 鉴权KEY
	$key      = "xxxx"; 
	$domain   = $schme.'://'.$host;
	$filename = parse_url($url,PHP_URL_PATH); 
	$sstring  = $filename."-".$time."-0-0-".$key;
	$md5      = md5($sstring);
	$auth_key = "auth_key=".$time."-0-0-".$md5;
	$url      = $domain.$filename."?".$auth_key;
	return $url;
}

function PrivateKeyB($url, $cdn_domain='mp.gstartec.cn'){
	$schme = parse_url($url,PHP_URL_SCHEME);  
	$host  = parse_url($url,PHP_URL_HOST);  
	$host  = str_replace($host, $cdn_domain, $host);
	$time  = date("YmdHi", strtotime('-28 minute'));
	// 鉴权KEY
	$key      = "xxxx";
	$domain   = $schme.'://'.$host;
	$filename = parse_url($url,PHP_URL_PATH); 
	$sstring  = $key.$time.$filename;
	$md5      = md5($sstring);
	$url      = $domain.'/'.$time."/".$md5.$filename;
	return $url;
}

$old_url = 'http://xxx.com/0e3e1c0e1c84cc9bd203e6b4504e8a1c.png';

$new_url = PrivateKeyB($old_url);
echo $new_url;
 ```