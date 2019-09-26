---
layout: post
title: "thinkphp下将url重写简化，无需配置ngixn"
keywords: ["thinkphp"]
description: "thinkphp下将url重写简化，无需配置ngixn"
category: "thinkphp"
tags: ["thinkphp"]
---

#### 最近有一需求是这样的
```
原网址
http://aa.test.com/book/book/article/id/99999933475129/book_id/99999917874/channel_id/4838/book_from/0.html
转化后的
http://aa.test.com/book/read/99999933475129/99999917874/4838/0.html
```

#### 需要保证不能跳转，打开后的网址就是,并且要支持翻页

```
http://aa.test.com/book/read/99999933475129/99999917874/4838/0.html
```


实现流程

1、在thinkphp配置文件添加路由，配置规则为` 静态地址和动态地址结合`
* 参考资料:[http://document.thinkphp.cn/manual_3_2.html#rule_route](http://document.thinkphp.cn/manual_3_2.html#rule_route)

```
    'DEFAULT_MODULE' => 'Book',
    'URL_REWRITE' => 3,
    'URL_MODEL' => 2,
    // 开启路由
    'URL_ROUTER_ON'   => true, 
    'URL_ROUTE_RULES'=>array(
        'read/:id/:book_id/:channel_id/:book_from' => array("book/article"),
    ), 
```

2、打开`http://aa.test.com/book/read/99999933475129/99999917874/4838/0.html`看是否能打开，如可以则表示成功

3、由于tp里没有生成路由规则的url, 为了保证生成的url也能支持路由功能，所以我们要在tp核心方式里面添加一小段代码。
在 `ThinkPHP\Common\functions.php` 里找U`方法，在`if ($suffix)`上面添加以下代码

```
if(C('URL_ROUTE_RULES')){
    foreach (C('URL_ROUTE_RULES') as $rule=>$real) {
        $real=$real[0];
        if(strpos($url, $real)!==false){
            $url = str_replace($real, $rule, $url);
            preg_match("/\/(\w+)\.php\/(\w+)/", $url, $match);
            if(isset($match[1]) && isset($match[2]) && $match[1][0]==$match[2][0]){
                $url = preg_replace("/\/(\w+)\.php/", '', $url);
            }elseif(strpos($url, 'index.php')!==false){
                $url = str_replace("/index.php", '', $url);
            }else{
                $url = str_replace(".php", '', $url);
            }
            preg_match_all("/(:\w+)/", $rule, $matches);
            foreach ((array)$matches[1] as $match) {
                $url = str_replace('/'.$match . '/', '/', $url);
                $url = str_replace('/'.substr($match, 1) . '/', '/', $url);
            }
        }   
    }   
}
```
