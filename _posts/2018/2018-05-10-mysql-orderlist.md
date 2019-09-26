---
layout: post
title: "统计-对比每个用户第一次和第二次下单情况"
keywords: ["msyql"]
description: "统计-对比每个用户第一次和第二次下单情况"
category: "msyql"
tags: ["msyql"]
---
{% include JB/setup %}

操作demo
```
-- 查询的语句如下
SET @num=1;
SET @last_uid=0;
SELECT uid, DATE_FORMAT(1_day,'%Y-%m-%d') AS 首次下单时间,1_money AS 首次下单金额,IFNULL(DATE_FORMAT(2_day,'%Y-%m-%d'),0) AS 第二次下单时间,
IF(2_money!=0,2_money,0) AS 第二次下单金额,
if( NOT ISNULL(2_company_id),IF(1_company_id=2_company_id,'是','否'),0) as 是否同一金额充值,IFNULL((TO_DAYS(2_day)-TO_DAYS(1_day)),0) AS 间隔天数 FROM (
SELECT 
uid,
GROUP_CONCAT(if(seq=1,addtime,NULL)) AS 1_day,
SUM(IF(seq=1,money,0)) AS 1_money,
GROUP_CONCAT(if(seq=1,gid,NULL)) AS 1_company_id,
GROUP_CONCAT(if(seq=2,addtime,NULL)) AS 2_day,
SUM(IF(seq=2,money,0)) AS 2_money,
GROUP_CONCAT(if(seq=2,gid,NULL)) AS 2_company_id
 FROM (
SELECT t1.*,IF(@last_uid=uid,@num:=@num+1,@num:=1) AS seq,
(@last_uid:=uid) AS tmp FROM (
SELECT uid,addtime,gid,money
FROM xs_orderlist

where status=1
GROUP BY uid desc,addtime ASC


) t1
) t2 GROUP BY uid
) t3
```

原文参考：[https://blog.csdn.net/hotmoy121/article/details/78262328?locationNum=7&fps=1](https://blog.csdn.net/hotmoy121/article/details/78262328?locationNum=7&fps=1)