---
layout: post
title: "golang操作串口"
keywords: ["golang"]
description: "golang操作串口"
category: "golang"
tags: ["golang"]
---
代码如下

```
package main

import (
	"github.com/xluohome/serial"
	"log"
)

func main() {
	c := &serial.Config{Name: "/dev/tty.wchusbserial1420", Baud: 9600}
	s, err := serial.OpenPort(c)
	if err != nil {
		log.Fatal(err)
	}

	txbuf := []byte{0x01, 0x05, 0x00, 0x00, 0x5A, 0x00, 0xF7, 0x6A}

	n, err := s.Write(txbuf)
	if err != nil {
		log.Fatal(err)
	}

	buf := make([]byte, 128)
	n, err = s.Read(buf)
	if err != nil {
		log.Fatal(err)
	}
	log.Printf("%X\n", buf[:n])
}
```