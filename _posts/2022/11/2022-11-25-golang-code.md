---
layout: post
title: "go常用，交集，并集，差集"
keywords: ["go"]
description: "go常用，交集，并集，差集"
category: "go"
tags: ["go"]
---

## 例子如下 
比较`all.txt`与`a1.txt`的差集合

```go
package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	f1 := "a1.txt"
	f2 := "all.txt"
	data1, err := ioutil.ReadFile(f1)
	data2, err2 := ioutil.ReadFile(f2)
	if err != nil && err2 != nil {
		println("读取文件失败")
	}
	dataLine1 := strings.Split(string(data1), "\r\n")
	dataLine2 := strings.Split(string(data2), "\r\n")
	//注意要把多的放左边
	set := difference(dataLine2, dataLine1)
	fmt.Printf("%v", len(dataLine1))
	println()
	fmt.Printf("%v", len(dataLine2))
	println()
	fmt.Println(set)

	// 以下为demo
	a := []string{"22", "33", "44"}
	b := []string{"22", "55", "43"}
	i := difference(a, b) //[33 44]
	fmt.Println(i)
}

// 求并集
func union(slice1, slice2 []string) []string {
	m := make(map[string]int)
	for _, v := range slice1 {
		m[v]++
	}

	for _, v := range slice2 {
		times, _ := m[v]
		if times == 0 {
			slice1 = append(slice1, v)
		}
	}
	return slice1
}

// 求交集
func intersect(slice1, slice2 []string) []string {
	m := make(map[string]int)
	nn := make([]string, 0)
	for _, v := range slice1 {
		m[v]++
	}

	for _, v := range slice2 {
		times, _ := m[v]
		if times == 1 {
			nn = append(nn, v)
		}
	}
	return nn
}

// 求差集 slice1-并集
func difference(slice1, slice2 []string) []string {
	m := make(map[string]int)
	nn := make([]string, 0)
	inter := intersect(slice1, slice2)
	for _, v := range inter {
		m[v]++
	}

	for _, value := range slice1 {
		times, _ := m[value]
		if times == 0 {
			nn = append(nn, value)
		}
	}
	return nn
}

```