---
layout: post
title: "php排序测试题"
keywords: ["竞赛"]
description: "php排序测试题"
category: "算法"
tags: ["php"]
---
{% include JB/setup %}

```
上周公司内部的一个小小的竞赛题，虽然题目不是很难，但是很遗憾没有做对！
还是要有待提高啊
```

### 题目
```
/**
 * 本轮题目内容：
 * 给一组一百个帮砍用户的openid，
 * 请找出帮砍次数最多的前十名用户的openid，按帮砍次数从大到小排序；如果有次数相同的，则以字母顺序排序；
 * 并找出帮砍次数最少的前十名，按帮砍次数从小到大排序；如果有次数相同的，则按字母顺序排序；
 */


### 答案!!

```php

<?php
/**
 * 本轮题目内容：
 * 给一组一百个帮砍用户的openid，
 * 请找出帮砍次数最多的前十名用户的openid，按帮砍次数从大到小排序；如果有次数相同的，则以字母顺序排序；
 * 并找出帮砍次数最少的前十名，按帮砍次数从小到大排序；如果有次数相同的，则按字母顺序排序；
 */

$data = array(
'oCF_hjg2sbQpNDzh0cyZCD0axjdU',
'oCF_hjgN4_2zTYOJVOu4Pcq4aJsU',
'oCF_hjiSOV_wT2AfZmsHTES32dSE',
'oCF_hjjW-bOL9joZbz1Ruc_imU8M',
'oCF_hjkwTaI_4I9aUNbOY96oTtQM',
'oCF_hjlQCKNyNTyvQn1STxzMWetE',
'oCF_hjmFAsg-IGENsF2MelwlmLwM',
'oCF_hjmOAPzo0g6bdBqJhn7NJ44Y',
'ojjs8t-dw-hDTM0a-E9JPJSx8iD0',
'ojjs8t-IX0XDySjq8ebBYL5tu9SM',
'ojjs8t-Ju4A6IaJxg0uQOJXrYofk',
'ojjs8t-LXD-UIcKx5dLwUWIae-8k',
'ojjs8t-MxbRjvhcKvEWjYnouwTXI',
'ojjs8t-NkNtaOytiY0E5Yi6XJqI4',
'ojjs8t-p47U-1L5qWkAz0X0oIS2U',
'ojjs8t-pkKQIYDuYxk3itAMebq28',
'oCF_hjnp55Yc0TXl17wYJSxY6EK4',
'oCF_hjodJmq7ktKfCRch7oMdvy1U',
'oCF_hjpqPTiFCSa9CEYq7W9S97uU',
'oCF_hjr1OGrRcfJ0F42CZGgnvCG4',
'oCF_hjs1XMJlmaXoTgjiJq4fAark',
'oCF_hjsCwy_MXMoDOtA8VyF9lgpk',
'oCF_hjsolm4_ffMfNIrp22ADNa6U',
'oCF_hjt1vOYkYb_AURq7Ka2nw6oQ',
'oCF_hjt3Xc6dAmBfzqtLE4dHFsII',
'oCF_hjtrkRcQP1NhNFrKvSPvKLrI',
'oCF_hju7TvCD1AKnHPUOOMZz8opw',
'ojjs8t-2dbSgaXFg3cHttWGUhCas',
'ojjs8t-bW3fko0_3W_ojlymGhbdM',
'ojjs8t-T7_RIcqGZjM9n4O0r8G3A',
'ojjs8t-v2dmNQj2IW0EOhkpS7gUQ',
'ojjs8t-Vk7LsGu7Y-pxqW0gYLWrY',
'ojjs8t-xS4CMtfNIrSPa8QM6AcfM',
'ojjs8t-_4ZB2h3pN7hqLJyp2rkv0',
'ojjs8t0-VTwurNTuDZpxDNysm4Ro',
'ojjs8t08HT8OqN2B3aEd-127Bjbc',
'ojjs8t0aB-KGIlc2CCFFLQfMqMKo',
'ojjs8t0e9_X5h8NkrfhZCA0csU4A',
'ojjs8t0Egb4ClHuD93GTJxTvjzyg',
'ojjs8t0HiZuGQgoqqZsq0j0t6Jiw',
'ojjs8t1oFPGia9ePJEYb_Qy3w16A',
'ojjs8t1QmioevSmLQFF2OWPVbR-g',
'ojjs8t1QSZycG56mdBLS9HwBt8K8',
'ojjs8t1RUpk0DG6UbWCzgCJ5n038',
'ojjs8t1timtnJoy_shSxoMuaBvOw',
'ojjs8t1woBu3pmXZvs_AKNsKkX74',
'ojjs8t2-txh3syFMI7JMA-veFp4Y',
'ojjs8t0iuc5mIayxpaG5-TkdKBl8',
'ojjs8t0NMFQNKLFMcg3tlPxcpWuM',
'ojjs8t0oqhYJ-AIKsMYMoWvYz_ow',
'ojjs8t0Pa2Tyc3htRObQgeY2XnjI',
'ojjs8t0p_DxPQHwc3lofeImvDr2k',
'ojjs8t0UMdTx1T-i9xjPa3npHRU4',
'ojjs8t0x8ORknEf9nDD-UZwq0raA',
'ojjs8t0zNG_GqjOiulfYADCJ7ri4',
'ojjs8t0ZSt_n6JdFmRMiLtS68CzA',
'ojjs8t10WsQD9FiBgVkzrBWWfhVE',
'ojjs8t17mJVlTco61cjDkryOyF-A',
'ojjs8t196G2hjUd3eyGFPlg9abWc',
'ojjs8t1aCpmXmcPOajrpYQH_C9l0',
'ojjs8t2cw3VUcq9AJUGM-7mK-_OQ',
'ojjs8t2dgaEsb61XthuVG2Grjg9s',
'ojjs8t2etL5g4gEkCejf34Bax6_I',
'ojjs8t2eU-EH9nkfyy2pe2d8DnQs',
'ojjs8t2FHS_N7ayLztMDTUm1Oemk',
'ojjs8t2gUBhpj6caYRvNVOR4i8IE',
'ojjs8t1bQ-51fyct2zLOb_kV5aUs',
'ojjs8t1CBCcN-glVMx7yw4BEW_hI',
'ojjs8t1d-QhZJEQB4Q78UXbQKVaw',
'ojjs8t1f3DNOiUZcdo5wvmobY3GU',
'ojjs8t1LGsU4GzEzGr1DIGF9AOhc',
'ojjs8t20ZxpEGtMNCe3F4sSjVf5M',
'ojjs8t25FKSj0Evme10h_S-i5_VQ',
'ojjs8t26Q2jFqh1yhH4oX5YR2Fvs',
'ojjs8t2aS6fAshShGfJKsNNSzajA',
'ojjs8t2aZwrkEJI41j2xRCQzCuNY',
'ojjs8t2b4UTtO76VnnxTCSB7SrY8',
'ojjs8t2R7lzhrQbPe6f2mej_ApOA',
'ojjs8t2Vqyz5asR6nZyzZf--mlB8',
'ojjs8t2FHS_N7ayLztMDTUm1Oemk',
'ojjs8t2gUBhpj6caYRvNVOR4i8IE',
'ojjs8t1bQ-51fyct2zLOb_kV5aUs',
'ojjs8t1CBCcN-glVMx7yw4BEW_hI',
'ojjs8t1d-QhZJEQB4Q78UXbQKVaw',
'oCF_hjnp55Yc0TXl17wYJSxY6EK4',
'oCF_hjodJmq7ktKfCRch7oMdvy1U',
'oCF_hjpqPTiFCSa9CEYq7W9S97uU',
'oCF_hjr1OGrRcfJ0F42CZGgnvCG4',
'oCF_hjs1XMJlmaXoTgjiJq4fAark',
'oCF_hjsCwy_MXMoDOtA8VyF9lgpk',
'oCF_hjr1OGrRcfJ0F42CZGgnvCG4',
'oCF_hjs1XMJlmaXoTgjiJq4fAark',
'oCF_hjsCwy_MXMoDOtA8VyF9lgpk',
'oCF_hjsolm4_ffMfNIrp22ADNa6U',
'oCF_hjt1vOYkYb_AURq7Ka2nw6oQ',
'oCF_hjt3Xc6dAmBfzqtLE4dHFsII',
'oCF_hjtrkRcQP1NhNFrKvSPvKLrI',
'ojjs8t1f3DNOiUZcdo5wvmobY3GU',
'ojjs8t1LGsU4GzEzGr1DIGF9AOhc',
'ojjs8t20ZxpEGtMNCe3F4sSjVf5M',
);

// 查找唯一数据
$user = array_unique ( $data );

// 算出结果
foreach ((array)$user as $key => $value) {
	$newUser[$value]['user'] =$value;
	$newUser[$value]['total'] = 0;
	foreach ($data as $key2 => $value2) {
		if($value2 == $value){
			$newUser[$value]['total'] +=1;
		}
	}	
}

//对结果对行字典排序
ksort($newUser);

// 重新得到数据
foreach ($newUser as $key => $value) {
	$testArr[] = $value;
}

// 排序算法
foreach ((array)$testArr as $key => $value)
 {
	$sortData[$key]['openid']  = $value['user'];
	$sortData[$key]['total']  = $value['total'];

	$sortList[$key] = $value['total'];
	$sortList2[$key] = $value['total'];
	$sortList3[$key] = $value['user'];
}

$asc = $sortData;
// 根据total 排序
array_multisort($sortList, SORT_ASC, $asc);

$desc = $sortData;
// 根据total 排序
array_multisort($sortList2, SORT_DESC, $desc);

// 从小到大
echo '---------------从小到大----------------';
foreach ($asc as $key => $value) 
{
	if($key < 10){
		echo "<br/>\n";
		$p = $key+1;
		echo '排名：'.$p."&nbsp;&nbsp;&nbsp;用户:" .$value['openid'].'&nbsp;&nbsp;&nbsp;[数量]'.$value['total']."<br>\n";
	}
}

echo '---------------从大到小----------------';
// 从大到小
foreach ($desc as $key => $value) 
{
	if($key < 10){
		echo "<br/>\n";
		$p = $key+1;
		echo '排名：'.$p."&nbsp;&nbsp;用户:" .$value['openid'].'&nbsp;&nbsp;&nbsp;[数量]'.$value['total']."<br>\n";
	}
}
?>

```

