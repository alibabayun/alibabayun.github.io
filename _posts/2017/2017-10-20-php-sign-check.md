---
layout: post
title: "php签名算法"
keywords: ["php签名算法"]
description: "php签名算法"
category: "签名"
tags: ["签名"]
---

```
    //签名算法
    public  function generateSignature($timestamp, $nonce, $privateKey = '') {
        $token = empty($privateKey) ? YUNAPPSECRET : $privateKey;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode( $tmpArr );
        $signature = sha1( $tmpStr );

        return $signature;
    }

    //检查签名算法
    public  function checkSignature($privateKey = '') {
 

        if (!isset($_GET["signature"])) {return false;}
        $signature = $_GET["signature"];
        $timestamp = $_GET["timestamp"];
        $nonce = $_GET["nonce"];
        
        $time = time();
        if ($time - $timestamp > 20) {
            return false;
        }

        $token = empty($privateKey) ? YUNAPPSECRET : $privateKey;
        $tmpArr = array($token, $timestamp, $nonce);
        sort($tmpArr, SORT_STRING);
        $tmpStr = implode( $tmpArr );
        $tmpStr = sha1( $tmpStr );
        if( $tmpStr == $signature ){
            return true;
        }else{

            return false;
        }
    }

    public function test_sign(){

        $timestamp = time();
        $nonce = 123546;
        $privateKey = 'privatekey';
        $sign = $this->generateSignature($timestamp, $nonce, $privateKey);
        $data = array();
        $data['signature'] = $sign;
        $data['timestamp'] = $timestamp;
        $data['nonce'] = $nonce;
        $url = U('demo2/test_sign_check', $data);
        redirect($url);exit;

    }

    public function test_sign_check(){
        $privateKey = 'privatekey';
        $is_ok = $this->checkSignature($privateKey);
        var_dump($is_ok);exit;
    }
```