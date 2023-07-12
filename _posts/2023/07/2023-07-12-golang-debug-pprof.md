---
layout: post
title: "如何排查Go程序CPU占用过高问题"
keywords: ["golang"]
description: "如何排查Go程序CPU占用过高问题"
category: "golang"
tags: ["golang"]
---

### 前言
如果要在 golang 开发过程中进行性能调优，一般需要使用 pprof，本文介绍的是 pprof 工具使用方法。

### 代码示例
```
mport (
 // 略
 _ "net/http/pprof" // 会自动注册 handler 到 http server，方便通过 http 接口获取程序运行采样报告
 // 略
)

func main() {
 // 略

 runtime.GOMAXPROCS(1) // 限制 CPU 使用数，避免过载
 runtime.SetMutexProfileFraction(1) // 开启对锁调用的跟踪
 runtime.SetBlockProfileRate(1) // 开启对阻塞操作的跟踪

 go func() {
  // 启动一个 http server，注意 pprof 相关的 handler 已经自动注册过了
  if err := http.ListenAndServe(":6060", nil); err != nil {
   log.Fatal(err)
  }
  os.Exit(0)
 }()

 // 略
}
```
在浏览器输入 `http://127.0.0.1:6060/debug/pprof/`

## 参考示例
1. [如何排查Go程序CPU占用过高问题](https://mp.weixin.qq.com/s/Zw6mDRDXtXKlKxQeit6gPQ)
2. [通过pprof定位groutine泄漏](https://zhuanlan.zhihu.com/p/477381377)
3. [实用go pprof使用指南](https://zhuanlan.zhihu.com/p/396363069)
