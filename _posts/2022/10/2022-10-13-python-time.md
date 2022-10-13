---
layout: post
title: "python时间函数"
keywords: ["python"]
description: "python时间函数"
category: "python"
tags: ["python"]
---



### 时间常用函数time
#### 取整型时间
```
print( int(time.time()) )
```

#### 时间戳 格式化时间
```
print(time.strftime("%Y-%m-%d %H:%I:%S"))
```

#### 将时间结构化，取年 月 日 时 分 秒 当前周 周年天
```
print(time.localtime( time.time() ))
#反过来
res = time.localtime( time.time() )
print( time.strftime('%Y-%m-%d %X', res ) )
```



#### 将结构化时间 转 时间戳
```
print(time.mktime(time.strptime('2022-01-01 00:00:00', '%Y-%m-%d %X'))) #得到时间戳
```

import datetime
## 时间datetime模板
#### 直接取字符当前时间
```
print(datetime.datetime.now().replace(microsecond=0))
```

#### 取多少天后的时间
```
res = datetime.datetime.now()+datetime.timedelta(days=+2) #取2天后的时间
print(res.replace(microsecond=0)) #不要毫秒
print(datetime.datetime.now()+datetime.timedelta(hours=+2)) #取2小时后的时间
```
#### 时间戳转时间
```
print(datetime.datetime.fromtimestamp(2323232323))
```