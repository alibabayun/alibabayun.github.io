---
layout: post
title: "composer使用"
keywords: ["php"]
description: "composer使用"
category: "php"
tags: ["php"]
---
## 使用阿里云composer镜像为国内composer加速

阿里云composer镜像与 Packagist 官方实时同步，推荐使用最新的 Composer 版本。
所有项目都会使用该镜像地址：

```
composer config -g repo.packagist composer https://mirrors.aliyun.com/composer/
```

取消配置还原到默认的地址：
```
composer config -g --unset repos.packagist
```

设置后可能需要`composer clear`清空一下缓存 

## 修改或创建`composer.json`的文件，添加
```
{
"require": {
    "qcloud/cos-sdk-v5": ">=2.0"
}
}
```

## 使用 Composer 安装，执行以下命令。
```
#新安装注意，如果有composer.lock文件要先删除
composer install

#直接更新，
composer update

```

##使用
参考资料：https://cloud.tencent.com/document/product/436/12266
```
$secretId = "COS_SECRETID"; //"云 API 密钥 SecretId";
$secretKey = "COS_SECRETKEY"; //"云 API 密钥 SecretKey";
$region = "COS_REGION"; //设置一个默认的存储桶地域
//$cosClient = new Qcloud\Cos\Client(
$cosClient = \new Qcloud\Cos\Client( //如果是tp此处可能要加一个反斜杠
   array(
       'region' => $region,
       'schema' => 'https', //协议头部，默认为http
       'credentials'=> array(
           'secretId'  => $secretId ,
           'secretKey' => $secretKey)));
```

