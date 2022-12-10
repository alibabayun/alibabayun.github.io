---
layout: post
title: "前端转入格式不统一时,gorm时间格式化"
keywords: ["go"]
description: "前端转入格式不统一时,gorm时间格式化"
category: "go"
tags: ["go"]
---

## 背景
GORM自带的`time.Time`类型JSON默认输出`RFC3339Nano`格式的，但是如果想改为`yyyy-MM-dd HH:mm:ss`形式的时间格式，需要定制`MarshalJSON了`。 以下是数据库里存储的数据：
![](https://img-blog.csdnimg.cn/f200b27e457442a8af77a4bc48bb8bc9.png)

## 解决
制作一个统一的工具类`time.go`
```go
package common

import (
	"database/sql/driver"
	"fmt"
	"time"
)

const timeFormat1 = "2006-01-02"
const timeFormat2 = "2006-01-02 15:04:05"
const timezone = "Asia/Shanghai"

type Time time.Time

func (t Time) MarshalJSON() ([]byte, error) {
	b := make([]byte, 0, len(timeFormat2)+2)
	b = append(b, '"')
	b = time.Time(t).AppendFormat(b, timeFormat2)
	b = append(b, '"')
	return b, nil
}

func (t *Time) UnmarshalJSON(data []byte) (err error) {
	timeFormat := timeFormat2
	if len(data) == 12 {
		timeFormat = timeFormat1
	}
	now, err := time.ParseInLocation(`"`+timeFormat+`"`, string(data), time.Local)
	*t = Time(now)
	return
}

func (t Time) String() string {
	return time.Time(t).Format(timeFormat2)
}

func (t Time) local() time.Time {
	loc, _ := time.LoadLocation(timezone)
	return time.Time(t).In(loc)
}

func (t Time) Value() (driver.Value, error) {
	var zeroTime time.Time
	var ti = time.Time(t)
	if ti.UnixNano() == zeroTime.UnixNano() {
		return nil, nil
	}
	return ti, nil
}

func (t *Time) Scan(v interface{}) error {
	value, ok := v.(time.Time)
	if ok {
		*t = Time(value)
		return nil
	}
	return fmt.Errorf("can not convert %v to timestamp", v)
}

```

## 如何使用
``` go
type IpBroadcast struct {
	ID               int         `gorm:"primary_key" json:"id"`                            //编号
	InstallTime      common.Time `gorm:"type:date" json:"installTime"`                     //安装时间
	TenantId         int         `gorm:"type:int" json:"tenantId"`                         //租户ID
	CreateTime       time.Time   `gorm:"type:datetime" json:"createTime"`                  //新增时间
	CreateUser       int64       `gorm:"type:bigint" json:"createUser"`                    //新增记录操作用户ID
	UpdateTime       time.Time   `gorm:"type:datetime" json:"updateTime"`                  //修改时间
	UpdateUser       int64       `gorm:"type:bigint" json:"updateUser"`                    //修改用户
	IsDeleted        int         `gorm:"type:int;default 0" json:"isDeleted"`              //是否删除 0=未删除,1=删除
}
```