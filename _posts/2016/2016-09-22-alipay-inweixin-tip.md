---
layout: post
title: "在微信提示 要用浏览器打开的一个页面"
keywords: ["在微信提示 要用浏览器打开的一个页面"]
description: "在微信提示 要用浏览器打开的一个页面"
category: "ASCII"
tags: ["ASCII"]
---
{% include JB/setup %}

![](https://img.alicdn.com/imgextra/i4/1819728314/TB2IDvfbBPzQeBjSZFhXXbRpFXa_!!1819728314.jpg)

```
<!DocType html>
<html>
    <meta http-equiv=Content-Type content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>支付提示</title>
    <style>.f10{font-size:10px}.f11{font-size:11px}.f12{font-size:12px}.f13{font-size:13px}.f14{font-size:14px}.f15{font-size:15px}.f16{font-size:16px}.f17{font-size:17px}.f18{font-size:18px}.f19{font-size:19px}.f20{font-size:20px}body{font-size:14px}h1,h2,h3,h4,h5{font-weight:400;font-style:normal}h1,.h1{font-size:20px}h2,.h2{font-size:18px}h3,.h3{font-size:16px}h4,.h4{font-size:14px}h5,.h5{font-size:12px}a,a:visited{color:#007aff}.text_color{color:#888}.title_color{color:#000}.desc{color:#b2b2b2}.warn{color:#b71414}.nickname{color:#576b95}.tips{font-size:13px;color:#b2b2b2}body{background-color:#fff}body.msg_dark{background-color:#2e3132;color:#fff}.page_msg{padding:75px 15px 0;text-align:center}.icon_area{margin-bottom:19px}.text_area{margin-bottom:25px}.text_area .title{margin-bottom:12px}.opr_area{margin-bottom:25px}.extra_area{margin-bottom:20px}@media screen and (min-height:416px){.extra_area{position:fixed;left:0;bottom:0;width:100%}}.btn{display:block;margin-left:auto;margin-right:auto;padding-left:14px;padding-right:14px;font-size:16px;text-align:center;text-decoration:none;overflow:visible;height:40px;border-radius:5px;-moz-border-radius:5px;-webkit-border-radius:5px;box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;color:#fff;line-height:40px;-webkit-tap-highlight-color:rgba(255,255,255,0)}.btn.btn_inline{display:inline-block}.btn_default{background-color:#d1d1d1}.btn_default:not(.btn_disabled):visited{color:#fff}.btn_default:not(.btn_disabled):active{color:rgba(255,255,255,.4);background-color:#a7a7a7}.btn_primary{background-color:#04be02}.btn_primary:not(.btn_disabled):visited{color:#fff}.btn_primary:not(.btn_disabled):active{color:rgba(255,255,255,.4);background-color:#039702}.btn_warn{background-color:#ef4f4f}.btn_warn:not(.btn_disabled):visited{color:#fff}.btn_warn:not(.btn_disabled):active{color:rgba(255,255,255,.4);background-color:#c13e3e}.btn.btn_mini{height:25px;line-height:25px;font-size:14px}button.btn,input.btn{width:100%;border:0;outline:0;-webkit-appearance:none}button.btn:focus,input.btn:focus{outline:0}button.btn_inline,input.btn_inline{width:auto}.btn_disabled{color:rgba(255,255,255,.6)}.btn+.btn{margin-top:10px}.btn.btn_inline+.btn.btn_inline{margin-top:auto;margin-left:10px}.btn_area{margin-left:-5px;margin-right:-5px;font-size:0}.btn_area.btn_area_inline{margin-left:auto;margin-right:auto;display:-webkit-box;display:-webkit-flex;display:-moz-box;display:-ms-flexbox;display:flex}.btn_area.btn_area_inline .btn{margin-top:auto;margin-right:10px;width:100%;-webkit-box-flex:1;-webkit-flex:1;-moz-box-flex:1;-ms-flex:1;box-flex:1;flex:1;display:inline-block \9;width:48% \9;margin-left:1% \9;margin-right:1% \9}.btn_area.btn_area_inline .btn:last-child{margin-right:0}span.btn button{display:block;width:100%;height:100%;background-color:transparent;border:0;outline:0;color:#fff}span.btn button:active{color:rgba(255,255,255,.4)}span.btn.btn_loading button,span.btn.btn_disabled button{color:#fff}.icon_msg{width:100px;height:100px;vertical-align:middle;display:inline-block;border-radius:50%;-moz-border-radius:50%;-webkit-border-radius:50%}.icon_msg.warn{background-color:#f86161;color:#fff;font-size:60px;font-style:normal}html{-ms-text-size-adjust:100%;-webkit-text-size-adjust:100%}body{font:14px/1.5em "Helvetica Neue",Helvetica,Arial,sans-serif;background-color:#efeff4;line-height:1.6}body,h1,h2,h3,h4,h5,p,ul,ol,dl,dd,fieldset,textarea{margin:0}fieldset,legend,textarea,input,button{padding:0}button,input,select,textarea{font-family:inherit;font-size:100%;margin:0;*font-family:"Helvetica Neue",Helvetica,Arial,sans-serif}ul,ol{padding-left:0;list-style-type:none}a img,fieldset{border:0}a{text-decoration:none}

    *{margin:0; padding:0;}
    img{max-width: 100%; height: auto;}
    .test{height: 600px; max-width: 600px; font-size: 40px;}
    </style>
</head>
<body>
<div class="body">
    <div class="page_msg">
                <div class="icon_area"><i class="icon_msg warn">!</i></div>
        <div class="text_area">
            <h2 class="title">请在微信外打开订单，进行支付</h2>
        </div>
         
    </div>
</div>
	<script type="text/javascript">
		function is_weixin() {
		    var ua = navigator.userAgent.toLowerCase();
		    if (ua.match(/MicroMessenger/i) == "micromessenger") {
		        return true;
		    } else {
		        return false;
		    }
		}
		var isWeixin = is_weixin();
		var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight;
		function loadHtml(){
			var div = document.createElement('div');
			div.id = 'weixin-tip';
			div.innerHTML = '<p onclick="hide_img();"><img id="img" src="https://img.alicdn.com/imgextra/i4/1819728314/TB29OndbxvzQeBjSZFgXXcvfVXa_!!1819728314.png" alt="微信打开"/></p>';
			document.body.appendChild(div);
		}
		
		function loadStyleText(cssText) {
	        var style = document.createElement('style');
	        style.rel = 'stylesheet';
	        style.type = 'text/css';
	        try {
	            style.appendChild(document.createTextNode(cssText));
	        } catch (e) {
	            style.styleSheet.cssText = cssText; //ie9以下
	        }
            var head=document.getElementsByTagName("head")[0]; //head标签之间加上style样式
            head.appendChild(style); 
	    }
	    var cssText = "#weixin-tip{position: fixed; left:0; top:0; background: rgba(0,0,0,0.8); filter:alpha(opacity=80); width: 100%; height:100%; z-index: 100;} #weixin-tip p{text-align: center; margin-top: 10%; padding:0 5%;}";
		if(isWeixin){
			loadHtml();
			loadStyleText(cssText);
		}
		
		document.getElementById('weixin-tip').onclick=function(){
			hide_img();
		};

		function hide_img(){
			document.getElementById("weixin-tip").style.display='none';
		}
	</script>
</body>
</html>
```