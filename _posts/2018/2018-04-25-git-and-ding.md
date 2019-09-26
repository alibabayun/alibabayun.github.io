---
layout: post
title: "利用钉钉和 git log每天作更新日志报表"
keywords: ["nginx"]
description: "用git log每天作更新日志报表"
category: "nginx"
tags: ["nginx"]
---
{% include JB/setup %}

## 背景
最近运营这边反馈过来一个问题，就是有时不知道开发这边更新了什么东西，有什么调整，导致数据有问题。
于是他们给了我们一个这个的规定，每个月他们都要看都更新了什么东西，本想直接给他们一个git账号（图形式的第三方后台）看的，但想不想还是不怎么靠谱（因为我知道他们不会每天看去看的）。 为了不想每个月去整理这些东西，加上之前用过钉钉的机器人，于是想着能不能把我们的日志每天更新到这个群里，把要看数据 的人拉进来看。

## 利用工具
* git文档中有提到
[git log 的文档](https://git-scm.com/book/zh/v1/Git-%E5%9F%BA%E7%A1%80-%E6%9F%A5%E7%9C%8B%E6%8F%90%E4%BA%A4%E5%8E%86%E5%8F%B2)

* 钉钉机器人文档
[钉钉自定义机器人文档](https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1)

## 写脚本
于是写了个简单的脚本，每天定时发git log 到群里。
```
#!/bin/sh
cd /data/git/book/
git checkout -- * 
git pull origin master #进入git目录 拉代码
today=`date +"%Y-%m-%d"`
upDir=/data/gitupdate/book/$today.md #保存每天更新的日志

if [ $? -eq 0 ];then
    echo "### $today日更新提醒(24小时以内)：" > $upDir

    git log --pretty=format:"* %s %an %ad \n"  --since=24.hours    --before="$today" --no-merges --date=short | uniq >>  $upDir #利用git log 生成24小时以内的日志记录

    markdownStr=`cat $upDir`
    postData='{
       "msgtype": "markdown",
       "markdown": {"title":"'$today'更新通知",
  "text": "'$markdownStr'"
       },
      "at": {
          "atMobiles": [
              "1825718XXXX"
          ], 
          "isAtAll": false
      }
   }'
echo $postData

#发送通知到群
  if  [ !-n $markdownStr ] ;then
    curl 'https://oapi.dingtalk.com/robot/send?access_token=00dca2243bd51d5cfab5929f79fd6df450ab223e3002df813d3214b2e307f92d' \
     -H 'Content-Type: application/json' \
     -d "${postData}"
  else
      echo "the markdownStr you input is $markdownStr"
  fi

else
    echo "代码拉去失败"
fi
```

## 效果图
![效果图](http://wx3.sinaimg.cn/mw690/0060lm7Tly1fqoxn9xthgj30h50eaq4x.jpg
)

