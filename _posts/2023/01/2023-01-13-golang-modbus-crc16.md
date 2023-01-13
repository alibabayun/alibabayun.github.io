---
layout: post
title: "使用golang实现计算CRC-16（modbus）"
keywords: ["golang"]
description: "使用golang实现计算CRC-16（modbus）"
category: "golang"
tags: ["golang"]
---

### 前言
通信领域中计算CRC是一种常用模式，现在使用golang来计算一组数据的校验值首先给出计算参考

### 代码 
第三方库
```
package main

import (
	"bytes"
	"encoding/binary"
	"fmt"
	"github.com/sigurn/crc16"
)

func main() {
	num := []byte{0x01, 0x03, 0x00, 0x07, 0x00, 0x02, 0x75, 0xCA}
	fmt.Printf("num = %v \n", num[:len(num)-2])
	modbus := crc16.CRC16_MODBUS
	table := crc16.MakeTable(modbus)
	crc := crc16.Checksum(num[:len(num)-2], table)
	fmt.Printf("CRC16_MODBUS: %X\n", crc) //高低位检验码

	int16buf := new(bytes.Buffer)                    //构建int16 输出
	binary.Write(int16buf, binary.LittleEndian, crc) //将int16  从小端转换为 byte数组
	fmt.Printf("write buf is: %X", int16buf.Bytes()) //低高位检验码
}
```

### 参考资料
[https://blog.csdn.net/starelegant/article/details/80418839](https://blog.csdn.net/starelegant/article/details/80418839)