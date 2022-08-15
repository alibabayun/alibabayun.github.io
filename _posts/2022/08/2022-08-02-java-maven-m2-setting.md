---
layout: post
title: "maven设置远程仓库"
keywords: ["java"]
description: "maven设置远程仓库"
category: "java"
tags: ["java"]
---


找到`.m2/settting.xml`填写如下

```
<settings
    xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <usePluginRegistry/>
    <offline/>
    <pluginGroups/>
    <servers/>
    <mirrors>


        <mirror>
            <id>youzan-nexus-snapshot</id>
            <name>Maven Repository Mirror running on maven.youzanyun.com</name>
            <url>http://maven.youzanyun.com/repository/maven-public</url>
            <mirrorOf>*</mirrorOf>
        </mirror>

    </mirrors>
    <proxies/>
    <profiles>
       
    </profiles>
    <activeProfiles/>
</settings>
```