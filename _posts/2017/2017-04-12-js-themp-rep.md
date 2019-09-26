---
layout: post
title: "记录一个js模板替换方法"
keywords: ["js", "templateRep"]
description: "记录一个js模板替换方法"
category: "js"
tags: ["templateRep", "js"]
---
{% include JB/setup %}

无技术含量，只作一个记录

```
/* 模板替换函数  */
/**
 * [_templateRep 模板替换函数]
 * @param  {[type]} templateStr [html模板]
 * @param  {[type]} mapData     [json数据]
 * @return {[type]}             [替换后的html]
 */
_templateRep = function (templateStr, mapData) {
    var re, key;
    for (key in mapData) {
        re = new RegExp('\\{' + key + '\\}','ig');
        templateStr = templateStr.replace(re, mapData[key]); //replace key with value
    }
    return templateStr;
}
```