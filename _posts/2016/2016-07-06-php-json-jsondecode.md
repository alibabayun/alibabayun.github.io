---
layout: post
title: "php如何将转义的json转换成array"
keywords: ["php如何将转义的json转换成array"]
description: "php如何将转义的json转换成array"
category: "php"
tags: ["php", "json"]
---
{% include JB/setup %}

#### 需求如下
将下面的转义后的json转换成数组

#### 源数据为：

```
{&quot;content&quot;:{&quot;data&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;,&quot;desc&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;,&quot;detail&quot;:[{&quot;type&quot;:&quot;str&quot;,&quot;value&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;}],&quot;type&quot;:0,&quot;user&quot;:{&quot;id&quot;:&quot;@2eb8b02ed214a151722b4ae973896074188ec4ac7f3a2e457cc69de52db4b223&quot;,&quot;name&quot;:&quot;unknown&quot;}},&quot;msg_id&quot;:&quot;2665478071232326293&quot;,&quot;msg_type_id&quot;:3,&quot;to_user_id&quot;:&quot;@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76&quot;,&quot;user&quot;:{&quot;id&quot;:&quot;@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76&quot;,&quot;name&quot;:&quot;unknown&quot;}}
```

#### 要求为：

```
array(5) {
  ["content"]=>
  array(5) {
    ["data"]=>
    string(15) "有钱一起赚"
    ["desc"]=>
    string(15) "有钱一起赚"
    ["detail"]=>
    array(1) {
      [0]=>
      array(2) {
        ["type"]=>
        string(3) "str"
        ["value"]=>
        string(15) "有钱一起赚"
      }
    }
    ["type"]=>
    int(0)
    ["user"]=>
    array(2) {
      ["id"]=>
      string(65) "@2eb8b02ed214a151722b4ae973896074188ec4ac7f3a2e457cc69de52db4b223"
      ["name"]=>
      string(7) "unknown"
    }
  }
  ["msg_id"]=>
  string(19) "2665478071232326293"
  ["msg_type_id"]=>
  int(3)
  ["to_user_id"]=>
  string(66) "@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76"
  ["user"]=>
  array(2) {
    ["id"]=>
    string(66) "@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76"
    ["name"]=>
    string(7) "unknown"
  }
}

```

#### 完整实现代码如下:
```
<?php
$str=<<<eof
{&quot;content&quot;:{&quot;data&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;,&quot;desc&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;,&quot;detail&quot;:[{&quot;type&quot;:&quot;str&quot;,&quot;value&quot;:&quot;\u6709\u94b1\u4e00\u8d77\u8d5a&quot;}],&quot;type&quot;:0,&quot;user&quot;:{&quot;id&quot;:&quot;@2eb8b02ed214a151722b4ae973896074188ec4ac7f3a2e457cc69de52db4b223&quot;,&quot;name&quot;:&quot;unknown&quot;}},&quot;msg_id&quot;:&quot;2665478071232326293&quot;,&quot;msg_type_id&quot;:3,&quot;to_user_id&quot;:&quot;@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76&quot;,&quot;user&quot;:{&quot;id&quot;:&quot;@@6a96c35ed848772e8e895b42779a0c610642ec0b5aeae570af05a9d2a11c5b76&quot;,&quot;name&quot;:&quot;unknown&quot;}}
eof;
$htmlspc_str = htmlspecialchars_decode($str, ENT_QUOTES);
$arr = json_decode($htmlspc_str, true);
var_dump ($arr);exit;
?>
```
