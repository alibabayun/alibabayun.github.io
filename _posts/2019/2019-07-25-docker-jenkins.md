---
layout: post
title: "Docker版本Jenkins的使用"
keywords: ["Jenkins"]
description: "Docker版本Jenkins的使用"
category: "Jenkins"
tags: ["Jenkins"]
---

#### 执行以下快速安装
```
docker run -d -e TZ="Asia/Shanghai" -e JAVA_OPTS=-Duser.timezone=Asia/Shanghai -p 8080:8080 -p 50000:50000 -v jenkins:/var/jenkins_home --name jenkins docker.io/jenkins/jenkins 
```

#### 安装完成后查看初始密码
```
docker exec jenkins tail /var/jenkins_home/secrets/initialAdminPassword
```

参考资料:[https://www.jianshu.com/p/0391e225e4a6](https://www.jianshu.com/p/0391e225e4a6)