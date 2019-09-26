---
layout: post
title: "js控制返回上一页的问题"
keywords: ["js"]
description: "js控制返回上一页的问题"
category: "js"
tags: ["js", "黑科技"]
---
{% include JB/setup %}

不多说，直接上代码

```
    function a() {
        document.getElementById('aa').innerHTML = '';
        document.getElementById('img').style.display = 'block';
    }
    function gotocj() {  
        window.location.href = '返回要去的链接';
    } 
    window.onhashchange = function () { 
        jp();
    };
    function hh() {
        history.pushState(history.length + 1, "message", "#" + new Date().getTime()); 
    } 
    function jp(){
        gotocj();
    } 
    setTimeout('hh();', 200);
```