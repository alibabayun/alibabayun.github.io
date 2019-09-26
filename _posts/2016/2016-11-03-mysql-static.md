---
layout: post
title: "mysql做统计报表的复杂查询"
keywords: ["mysql做统计报表的复杂查询"]
description: "mysql做统计报表的复杂查询"
category: "mysql"
tags: ["mysql"]
---
{% include JB/setup %}

mysql做统计报表的复杂查询，记录下方便下次不会忘记

```
	$sql = "
		SELECT (@channel_id:=id) AS channel_id,
			   book_id,
		       @starttime AS startime,
		       @endtime AS endtime,
		       names,
		       (@cost_money :=
		          (SELECT SUM(cost_money)
		           FROM xs_channel_cost
		           WHERE channel_id=@channel_id
		             AND 
		             (start_time<=@endtime)
		             )) AS cost_money,
		       (@sub_num :=
		          (SELECT COUNT(id) AS sub_num
		           FROM xs_user
		           WHERE channel_id=@channel_id
		             AND addtime>=@starttime
		             AND addtime<=@endtime)) AS sub_num,
		       (@online_num :=
		          (SELECT SUM(online_num) AS online_num
		           FROM xs_channel_tongji
		           WHERE channel_id=@channel_id
		             AND addtime>=@starttime
		             AND addtime<=@endtime)) AS online_num,
		       ROUND((@online_num/@sub_num), 2) AS hyl,
		       (@pay_money :=
		          (SELECT SUM(pay_money) AS pay_money
		           FROM xs_channel_tongji
		           WHERE channel_id=@channel_id
		             AND addtime>=@starttime
		             AND addtime<=@endtime)) AS pay_money,
		       ROUND(@pay_money/@cost_money, 2) AS pay_lyhbr,
		       (@pay_people_num :=
		          (SELECT SUM(pay_num) AS pay_people_num
		           FROM xs_channel_tongji
		           WHERE channel_id=@channel_id
		             AND addtime>=@starttime
		             AND addtime<=@endtime)) AS pay_people_num,
		       ROUND(@pay_money/@pay_people_num, 2) AS pay_arpu,
		       (@pay_total_money :=
		          (SELECT SUM(pay_money) AS pay_total_money
		           FROM xs_channel_tongji
		           WHERE channel_id=@channel_id)) AS pay_total_money,
		       ROUND(@pay_total_money/@cost_money, 2) AS pay_total_lyhbr,
		       (@use_money :=ROUND(
		                             (SELECT SUM(use_coins) AS use_coins
		                              FROM xs_channel_tongji
		                              WHERE channel_id=@channel_id
		                                AND addtime>=@starttime
		                                AND addtime<=@endtime)/100, 2)) AS use_money,
		       ROUND(@use_money/@cost_money, 2) AS use_lyhbr,
		       (@use_people_num:=
		          (SELECT sum(use_people_num) AS use_people_num
		           FROM xs_channel_tongji
		           WHERE channel_id=@channel_id
		             AND addtime>=@starttime
		             AND addtime<=@endtime )) AS use_people_num,
		       ROUND(@use_money/@use_people_num, 2) AS use_arpu,
		       (@use_total_money :=ROUND(
		                                   (SELECT SUM(use_coins) AS use_coins
		                                    FROM xs_channel_tongji
		                                    WHERE channel_id=@channel_id)/100, 2)) AS use_total_money,
		       ROUND(@use_total_money/@cost_money, 2) AS use_total_lyhbr
		FROM xs_channel,
		    (SELECT(@starttime:='$start_time')) b,
		    (SELECT(@endtime:='$end_time')) c

		where
			$where
		LIMIT $limit
	";
```