---
layout: post
title: "php计算中文字的长度"
keywords: ["php计算中文字的长度"]
description: "php计算中文字的长度"
category: "php"
tags: ["php"]
---
{% include JB/setup %}

最近有一个需求，要求统计中文字的长度
查找了一些资料，终于有了一个相对完整且比较接近真实字数的code

特此记录一下 ^_^


````
/**
 * 	统计 中文字数，已去除 html 代码
 * @param  {[type]} $string [description]
 * @return {[type]}         [description]
 */
function cutstr_html($string)
{  
    $string = htmlspecialchars_decode($string, ENT_QUOTES );
    $string = strip_tags($string);  
    $string = eregi_replace("[[:alnum:]]|[[:punct:]]|[[:space:]]",'',$string);
    $string = trim($string);  
    $string = str_replace("\t","",$string);  
    $string = str_replace("\r\n","",$string);  
    $string = str_replace("\r","",$string);  
    $string = str_replace("\n","",$string);  
    $string = str_replace(" ","",$string);  
    $str =  trim($string);  
    return mb_strlen($str);
}
````