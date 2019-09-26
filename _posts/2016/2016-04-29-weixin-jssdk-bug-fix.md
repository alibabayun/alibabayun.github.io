---
layout: post
title: "微信分享签名异步加载问题？"
keywords: ["签名报错"]
description: "签名报错"
category: "微信"
tags: ["签名报错", "微信"]
---
{% include JB/setup %}


#### 背景

第一次使用时，签名是没问题的，但是分享后，从分享的链接进去，就出现js签名不正确的情况
这个问题一直没办法，相信也困扰了不少同学


#### 分析

1. 第一次分享没问题，但从分享的链接进就不能用了

2. 对比之下，仅仅是参数上多了一段这个参数而且是微信自带的`?from=singlemessage&isappinstalled=0`

3. 以为是php与js的urledcodo参与会影响于是，分别打印出当前的网站

前端：
<pre>
	$.get( '/wxsign/getwxconf.html', {url:myUrl}, function(remoteData){
		// console.log(remoteData);
		//向服务器发送请求，获得signature
		wx.config({
		  debug: false, // 开启或关闭调试模式,调用的所有api的返回值会在客户端alert出来
		  appId: remoteData.appId, // 必填，公众号的唯一标识
		  timestamp: remoteData.timestamp, // 必填，生成签名的时间戳
		  nonceStr: remoteData.nonceStr, // 必填，生成签名的随机串
		  signature: remoteData.signature,// 必填，签名，见附录1
		  jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','chooseImage','uploadImage','downloadImage'] // 必填，需要使用的JS接口列表
		});
		// 必须在wx.ready方法下执行
		wx.ready(function(){
		});
		wx.error(function(res){
		  // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		  // 分享统计
		   _czc.push(["_trackEvent","分享签名出错",'error', res, myUrl]);
		});
	}, 'json')
</pre>

后端：
<pre>
	$real_url = 'http://hero.test.com/zhuangbiauto/edit/id/7?from=singlemessage&isappinstalled=0'; //写死当前网址
	$js_from_url = urldecode($_GET['url']);// 用js get 方式传过来的
	if(strlen($real_url) == strlen($js_from_url) )
		echo '我是相等的'
	else
		echo '我们不一样哦';
</pre>

奇迹出现了，原来他们真的是不相等的，但是直接输出是一样的，仅仅是长度不一样！


#### 解决方案
将前端url传输改成POST方式，完美运行了
<pre>
    $.post( '/wxsign/getwxconf.html', {url:myUrl}, function(remoteData){
          // console.log(remoteData);
          //向服务器发送请求，获得signature
          wx.config({
              debug: false, // 开启或关闭调试模式,调用的所有api的返回值会在客户端alert出来
              appId: remoteData.appId, // 必填，公众号的唯一标识
              timestamp: remoteData.timestamp, // 必填，生成签名的时间戳
              nonceStr: remoteData.nonceStr, // 必填，生成签名的随机串
              signature: remoteData.signature,// 必填，签名，见附录1
              jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','chooseImage','uploadImage','downloadImage'] // 必填，需要使用的JS接口列表
          });
          // 必须在wx.ready方法下执行
          wx.ready(function(){
              setWxData()
          });
          wx.error(function(res){
              // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
              // 分享统计
               _czc.push(["_trackEvent","分享签名出错",'error', res, myUrl]);
          });
     
    }, 'json')
</pre>

#### 总结
1. js用get方式传输数据时会进行一定的处理，与服务端的会有一定的差异
2. 得到微信分享签名时，不能使用jsonp的方式去得到签名！要传输当前的url过去只能使用post方式