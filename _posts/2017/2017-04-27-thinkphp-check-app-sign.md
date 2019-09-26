---
layout: post
title: "【thinkphp】app接口签名+验证签名"
keywords: ["验证签名"]
description: "【thinkphp】app接口签名+验证签名"
category: "验证签名"
tags: ["验证签名"]
---

【thinkphp】app接口签名+验证签名
app接口签名+验证签名
IndexController.class.php

```
<?php
namespace Home\Controller;
use Think\Controller;
class IndexController extends Controller {
    public function index(){
        //缓存token过期时间
        F(session_id().'tokenTime',time() + 1200);
        $this->display();
    }
    /*
     * 接受数据
     */
    public function getMas(){
        //接受token参数，强制转换字符串
        $token=I('post.token/s');
        //验证
        $check=checkToken($token);
        if ($check== 10001){
            $this->ajaxReturn("接口时间过期");
        }elseif ($check== 10002){
            $this->ajaxReturn("非法调用接口");
        }elseif ($check== 10003){
            $this->ajaxReturn("正常！");
        }
        
    }
}
```

Common\function.php

```
/*
 * 验证token
 * 10001 时间过期
 * 10002 签名失败
 * 10003 验证通过
 */
function checkToken($token){
    //生成当前要验证的token
    $check=md5(session_id().'str1');
    //取过期时间
    $tokenTime=S(session_id().'tokenTime');
    //截取接收到的token里面的时间
    $time=substr($token,32);
    //截取接收到的token md5
    $token=substr($token,0,32);
    //判断是否过期
    if ($tokenTime>$time){
        //没有过期   
        if ($check == $token){
            //更新token过期时间
            F(session_id().'tokenTime',time() + 1200);
            //返回正常
            return 10003;
        }else {
            //签名验证失败
            return 10002;
        }
    }else {
        //返回过期，并且清空缓存
        S(session_id().'tokenTime',NULL);
        return 10001;
    }
}
```

模板写法

```
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>测试</title>
        <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js" type="text/javascript" charset="utf-8"></script>
    </head>
    <body>
        <input type="text" name="mas" id="mas" value="" />
        <input type="button" id="tijiao" value="点击发送" onclick="getMasg()" />
        <input type="hidden" name="token" id="token" value="{:md5(session_id().'str1').base64_encode(time())}" />
        
        <p>回复的消息：<span id="str"></span></p>
    </body>
    <script type="text/javascript">
        function getMasg(){
            $.ajax({
                url:'{:U('Home/Index/getMas')}',
                type:'POST', //GET
                data:{
                    token:$("#token").val(),
                },
                success:function(data){
                    $("#str").text(data)
                    console.log(data)
                },
            });            
        }
    </script>
</html>
```