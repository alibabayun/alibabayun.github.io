---
layout: post
title: "python在本地配置wss"
keywords: ["python"]
description: "python在本地配置wss"
category: "python"
tags: ["python"]
---

## 前言
因https下的ws不能直接使用，需要使用wss，所以开发时需要在本地配置以下证书，
Mac系统下生成ssl证书 1.生成私钥 2.生成证书 3.生成服务器的私钥，去除密钥口令 4.使用私钥为证书请求签名，生成给服务器签署的证书，格式是x509的PEM格式


## 生成证书
```
openssl req -x509 -nodes -new -sha256 -days 1024 -newkey rsa:2048 -keyout localhost.key -out localhost.pem -subj "/C=US/CN=Example-Root-CA"
```

## 示例Server代码
```
#!/usr/bin/env python

# WSS (WS over TLS) server example, with a self-signed certificate

import asyncio
import pathlib
import ssl
import websockets

async def hello(websocket, path):
    name = await websocket.recv()
    print(f"< {name}")

    greeting = f"Hello {name}!"

    await websocket.send(greeting)
    print(f"> {greeting}")

ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
localhost_pem = pathlib.Path(__file__).with_name("key_cert.pem")
ssl_context.load_cert_chain(localhost_pem)

start_server = websockets.serve(
    hello, "localhost", 8765, ssl=ssl_context
)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
```

## 示例Client代码
```
#!/usr/bin/env python

# WSS (WS over TLS) client example, with a self-signed certificate

import asyncio
import pathlib
import ssl
import websockets

ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
localhost_pem = pathlib.Path(__file__).with_name("key_cert.pem")
ssl_context.load_verify_locations(localhost_pem)

async def hello():
    uri = "wss://localhost:8765"
    async with websockets.connect(
        uri, ssl=ssl_context
    ) as websocket:
        name = input("What's your name? ")

        await websocket.send(name)
        print(f"> {name}")

        greeting = await websocket.recv()
        print(f"< {greeting}")

asyncio.get_event_loop().run_until_complete(hello())
```

## js示例
```
var socket = new WebSocket("wss://localhost:8765/");

socket.onopen = function(e) {
  alert("[open] Connection established");
  alert("Sending to server");
  socket.send("My name is John");
};

socket.onmessage = function(event) {
  alert(`[message] Data received from server: ${event.data}`);
};

socket.onclose = function(event) {
  if (event.wasClean) {
    alert(`[close] Connection closed cleanly, code=${event.code} reason=${event.reason}`);
  } else {
    // e.g. server process killed or network down
    // event.code is usually 1006 in this case
    alert('[close1] Connection died');
  }
};

socket.onerror = function(error) {
  alert(`[error2] ${error.message}`);
};
ƒ (error) {
  alert(`[error2] ${error.message}`);
}

```
## 注意事项
1. chrome 缓存：可能不会生效需要在浏览器`chrome://flags/#allow-insecure-localhost`打开`Enable`
2. mac下添加本地证书 参考 
[https://www.cnblogs.com/snandy/p/3262661.html](https://www.cnblogs.com/snandy/p/3262661.html)
3. `key_cert.pem`是将这2个文件合并成一个`localhost.key`和`localhost.pem`

## 参考链接

[https://github.com/aaugustin/websockets/issues/876](https://github.com/aaugustin/websockets/issues/876)