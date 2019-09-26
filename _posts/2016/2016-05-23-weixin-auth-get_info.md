---
layout: post
title: "网页授权获取微信用户信息错误40029"
keywords: ["网页授权获取微信用户信息错误40029"]
description: "微信网页授权"
category: "40029"
tags: ["40029", "微信网页授权"]
---
{% include JB/setup %}

#### 问题描述
- 这几天测试刚完成的网页授权获取微信用户信息功能。

- 在第一步：用户同意授权获取`code`，通过`code`获取`access_token`时，有时会出现`40029`错误。

- 经过调试，发现问题出现在`redirect_uri=REDIRECT_URI`当跳转到授权链接后，微信会发出两次转向至`redirect_uri`的相同请求（两次带进来的code是相同的）。

- 第一次的`code`后已经成功换取得`openid`以及`access_token`；

- 第二次转向到`redirect_uri`时，该`code`已经失效（`code`只能使用一次），从而导致了`40029`：不合法的`oauth_code`的错误，不能再获取到`access_token`。

- 由于面一次被终止，生效的为第二次，因而不能获取到用户信息。（可这种情况只是偶尔发生，过一会儿再进入又正常了），这个问题应该如何解决？


#### 可能出现的场景：

- 用户授权后，点了手机上的返回按钮，导致使用了相同的`code`去得到用户资料

#### 解决的办法
- 当出现`40029`的错误时再进行进行一次授权 

```
if($rs['errcode'] == 40029) {
    // 如果授权失败，则再进行一次授权
    $return_url = urlencode('http://' . $_SERVER["HTTP_HOST"] . $_SERVER["REQUEST_URI"]);
    $url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=' . C('AppID') . '&redirect_uri=' . $return_url . '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect'; 
    header("location:$url");
    die('重新授权');
}
```