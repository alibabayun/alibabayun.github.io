---
layout: post
title: "golang时间区间的所有天数与月份数"
keywords: ["golang"]
description: "golang时间区间的所有天数与月份数"
category: "golang"
tags: ["golang"]
---

本例子只是记录，2个时间以的所有天数与月份数，记录一下

```go
package main

import (
	"fmt"
	"time"
)

func main() {
	start := "2022-01-01"
	end := "2022-12-01"
	list := GetTimeDays(start, end)
	fmt.Printf("list = %v \n", list)

	months := GetTimeMonths(start, end)
	fmt.Printf("months = %v \n", months)

}

// GetTimeDays 时间区间的所有天数
func GetTimeDays(start_time, stop_time string) []string {
	tm1, _ := time.Parse("2006-01-02", start_time)
	tm2, _ := time.Parse("2006-01-02", stop_time)
	sInt := tm1.Unix()
	eInt := tm2.Unix()
	var args []string
	for {
		sInt += 86400
		st := time.Unix(sInt, 0).Format("2006-01-02")
		args = append(args, st)
		if sInt > eInt {
			break
		}
	}
	var days []string
	for i := 0; i < len(args); i++ {
		parse, _ := time.Parse("2006-01-02", start_time)
		date := parse.AddDate(0, 0, i).Format("2006-01-02")
		days = append(days, date)
	}
	return days
}

// GetTimeMonths 时间区间的所有月份
func GetTimeMonths(start_time, stop_time string) []string {
	tm1, _ := time.Parse("2006-01", start_time)
	tm2, _ := time.Parse("2006-01", stop_time)
	sInt := tm1.Unix()
	eInt := tm2.Unix()
	var args []string
	for {
		sInt += 86400
		st := time.Unix(sInt, 0).Format("20060102")
		args = append(args, st)
		if sInt > eInt {
			break
		}
	}
	var months []string
	i := 0
	for {
		parse, _ := time.Parse("2006-01-02", start_time)
		endStr, _ := time.Parse("2006-01-02", stop_time)
		month := parse.AddDate(0, i, 0).Format("2006-01")
		months = append(months, month)
		if month == endStr.Format("2006-01") || i > 24 {
			break
		}
		i++
	}
	return months
}

```