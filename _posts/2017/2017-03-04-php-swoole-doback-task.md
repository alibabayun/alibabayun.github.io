---
layout: post
title: "使用swoole在后台异步执行任务"
keywords: ["Centos", "swoole"]
description: "使用swoole在后台异步执行任务"
category: "swoole"
tags: ["swoole", "Centos"]
---
{% include JB/setup %}


最近有一个这样的需求，需要在后台能够群发微信客服消息，与模板消息
且线上环境粉丝数量多达数十万

##### 没使用swoole前：
在本地测试时，使用的测试账号粉丝数量较少，群发时用foreach请求接口时，发的速度也蛮快（因为不超过100），
但是上线上，发送时，就卡了，因为同时请求数量较多

最后，所以我们决定使用了swoole异步去执行发送的动作

##### 以下放出测试代码

Server
```
<?php
class Server
{
    private $serv;
    public function __construct() {
        $this->serv = new swoole_server("127.0.0.1", 9501);
        $this->serv->set(array(
            'worker_num' => 1,   //一般设置为服务器CPU数的1-4倍
            'daemonize' => 1,  //以守护进程执行
            'max_request' => 10000,
            'dispatch_mode' => 2,
            'task_worker_num' => 1,  //task进程的数量
            "task_ipc_mode " => 3 ,  //使用消息队列通信，并设置为争抢模式
            "log_file" => "aa.log" ,//日志
        ));
        $this->serv->on('Receive', array($this, 'onReceive'));
        // bind callback
        $this->serv->on('Task', array($this, 'onTask'));
        $this->serv->on('Finish', array($this, 'onFinish'));
        $this->serv->start();
    }
    public function onReceive( swoole_server $serv, $fd, $from_id, $data ) {
        //echo "Get Message From Client {$fd}:{$data}n";
        // send a task to task worker.
        $serv->task( $data );
    }
    public function onTask($serv,$task_id,$from_id, $data) {
        $array = json_decode( $data , true );
        if ($array['url']) {
            return $this->httpGet( $array['url'] , $array['param']  );
        }
    }
    public function onFinish($serv,$task_id, $data) {
        //echo "Task {$task_id} finishn";
        //echo "Result: {$data}n";
    }
    protected function httpGet($url,$data){
        if ($data) {
            $url .='?'.http_build_query($data) ;
        }
        $curlObj = curl_init();    //初始化curl，
        curl_setopt($curlObj, CURLOPT_URL, $url);   //设置网址
        curl_setopt($curlObj, CURLOPT_RETURNTRANSFER, 1);  //将curl_exec的结果返回
        curl_setopt($curlObj, CURLOPT_SSL_VERIFYPEER, FALSE);
        curl_setopt($curlObj, CURLOPT_SSL_VERIFYHOST, FALSE);   
        curl_setopt($curlObj, CURLOPT_HEADER, 0);         //是否输出返回头信息
        $response = curl_exec($curlObj);   //执行
        curl_close($curlObj);          //关闭会话
        return $response;
    }
}
// 调用
$server = new Server();

```
Client

```
<?php
class Client
{
    private $client;
    public function __construct() {
        $this->client = new swoole_client(SWOOLE_SOCK_TCP);
    }
    public function connect() {
        if( !$this->client->connect("127.0.0.1", 9501 , 1) ) {
            echo "Connect Error";
        }
        $data = array(
            "url" =>  "http://***/demo/send_msg" ,
            "param" => array(
                "username"=>'test',
                "password" => 'test'
                )
            );
        $json_data = json_encode($data);
        echo $this->client->send( $json_data );
    }
}
$client = new Client();
$client->connect();
```
##### Swoole安装
1、Install via pecl

```
pecl install swoole
````
2、Install from source

```
sudo apt-get install php5-dev
git clone https://github.com/swoole/swoole-src.git
cd swoole-src
phpize
./configure
make && make install
```
##### 配置php.ini
编译安装成功后，修改php.ini加入

```
extension=swoole.so
```
##### Swoole安装可参考这里
1、[https://git.oschina.net/swoole/swoole](https://git.oschina.net/swoole/swoole)
2、[https://wiki.swoole.com/wiki/page/6.html](https://wiki.swoole.com/wiki/page/6.html)