---
layout: post
title: "php 导出CSV函数 支持繁体"
keywords: ["php 导出CSV函数 支持繁体#号"]
description: "php 导出CSV函数 支持繁体"
category: "CSV"
tags: ["CSV", "小技能"]
---
{% include JB/setup %}

### 使用demo

```
    $tempArray = array(
        0=> array('name' => '姓名0'),
        1=> array('name' => '姓名1'),
        2=> array('name' => '姓名2'),
    );
    $nameArray = array('name' => '名字');
    
    csv($nameArray,$tempArray,'预登记资料表');
```

### php 导出CSV函数 支持繁体

```
    /**
    * 导出 CSV 通用模块
    * @access public
    * @param  array  $nameArr 要 array('导出的表中的字段'=>'中文命名'，'导出的表中的字段'=>'中文命名')
    * @return array  $sqlArr  数据库的查出的数据
    * @return string $name    导出CSV 时的命名
   */
    function csv($nameArr="",$sqlArr="",$name=""){
         foreach ($nameArr as $key => $value) {
           iconv('utf-8','gbk',$value); //转为中文
           $str[]= $value;
         }
         $str = implode(',', $str);
         $str .= "\n"; //用引文逗号分开  
         foreach ($sqlArr as $key => $value) {
             $array = array_change_key_case($value,CASE_LOWER);
             $chanJi = array_diff_key($array,$nameArr);
             $jiaoji[] = array_diff_key($array,$chanJi);
         } 
         $jiaoji = array_values($jiaoji);
         foreach ($jiaoji as $key => $value) {
           $arrValues[] = implode(',', $value);
         }
         $string = implode("\n", $arrValues);
         $str .= $string;
         $str = iconv('utf-8','gbk//TRANSLIT',$str); //转为中文           
         /*去除特殊符号*/
         $regex = "/\/|\~|\!|\|\#|\\$|\%|\^|\&|\*|\(|\)|\_|\+|\{|\}|\<|\>|\?|\[|\]|\|\.|\/|\;|\'|\`|\=|\\\|\|/";
         $str = preg_replace($regex,"",$str);
         $filename = "$name".date('YmdHis').'.csv'; //设置文件名
         $data = $str;
         header("Content-type:text/csv");   
         header("Content-Disposition:attachment;filename=".$filename);   
         header('Cache-Control:must-revalidate,post-check=0,pre-check=0');   
         header('Expires:0');   
         header('Pragma:public');   
         echo $data;  
    }
```