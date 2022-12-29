---
layout: post
title: "golang对Slice中的value排序"
keywords: ["go"]
description: "golang对Slice中的value排序"
category: "go"
tags: ["go"]
---
## 前言
golang对Slice中的value排序, golang对数组中的value排序

## 代码如下
```go
type tmpCaptureArea struct {
	Name  string `json:"name"`
	Value int    `json:"value"` //需要排序字段
}
type IntSlice []tmpCaptureArea
func (p IntSlice) Len() int           { return len(p) }
func (p IntSlice) Less(i, j int) bool { return p[i].Value < p[j].Value }
func (p IntSlice) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }

//将原来 var tmpCaptureAreaArr []tmpCaptureArea 换成
var tmpCaptureAreaArr IntSlice
tmpCaptureAreaArr = append(tmpCaptureAreaArr, tmpCaptureArea{
  Name: "name1",
  Value: 10,
})
tmpCaptureAreaArr = append(tmpCaptureAreaArr, tmpCaptureArea{
  Name: "name2",
  Value: 12,
})
tmpCaptureAreaArr = append(tmpCaptureAreaArr, tmpCaptureArea{
  Name: "name3",
  Value: 8,
})
sort.Sort(sort.Reverse(tmpCaptureAreaArr)) //排序
```