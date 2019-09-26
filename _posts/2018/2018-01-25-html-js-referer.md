---
layout: post
title: "Referer伪造,防盗链与反盗链相关"
keywords: ["js", "linux"]
description: "Referer伪造,防盗链与反盗链相关"
category: "linux"
tags: ["js", "Referer"]
---
{% include JB/setup %}
### 问题背景
* 最近在做一个跳转到第三方的页面链接的时候，发现了他们做了防盗的限制。只允许他们自己的域名。在网上找了一下看这个资料可以跳过限制，记录下


### Referer简介

- 简单来说，Referer是HTTP协议中的一个请求报头，用于告知服务器用户的来源页面。比如说你从Google搜索结果中点击进入了某个页面，那么该次HTTP请求中的Referer就是Google搜索结果页面的地址。如果你的某篇博客中引用了其他地方的一张图片，那么对该图片的HTTP请求中的Referer就是你那篇博客的地址。
一般Referer主要用于统计，像CNZZ、百度统计等可以通过Referer统计访问流量的来源和搜索的关键词（包含在URL中）等等，方便站长们有针性对的进行推广和SEO什么的
当然Referer另一个用处就是防盗链了，主要是图片和网盘服务器使用的较多。盗链的危害不言而喻，侵犯了版权不说，增加了服务器的负荷，却没有给真正的服务提供者带来实际利益（广告点击什么的）

- 另外要注意的是，Referer是由浏览器自动为我们加上的

### 以下情况是不带Referer的

1. 直接输入网址或通过浏览器书签访问
2. 使用 JavaScript 的 Location.href 或者是 Location.replace()
3. HTTPS等加密协议
4. 当然你可以通过在Chrome或者Firefox浏览器中安装一些插件去除Referer甚至进行Referer欺骗。如果是自己写爬虫的话，Referer是完全受我们掌控的，想怎么改就怎么改~
5. 使用html5中noreferrer
```
<a href="/test/index.php?noreferer" rel="noreferrer" target="_blank">noreferrer</a>
<a href="/test/index.php?noreferer" rel="noreferrer" target="_blank">noreferrer</a>
```
很明显IE8以及以下肯定是不支持的
chrome正常点击的话没问题，但是假如是右击链接然后重新窗口或标签打开，同样会传递referer
6. 使用 meta refresh 重定向的网址。
这个会多出来个跳转页面，
IE8支持，chrome同样携带referer


### 反反盗链


- 反盗链的方法这里就不多说了，网上一搜一箩筐，不同平台有不同的实现方法。

- 加入反盗链机制后，从其他非服务提供者指定的来源的HTTP请求就得不到正常结果了，比如百度的反盗链机制~

- 当然，访问用户可以通过给浏览器安装一些插件去除Referer来正常显示，但是并非每一个用户都那么爱折腾。有没有一个简单粗暴跨平台跨浏览器的服务器端解决方案呢？也就是说访问用户什么都不用做就可以正常显示。

- 同样是百度域名下面的图片，为什么一张可以正常显示，另一张就显示盗链呢？我们来抓包看看
图略

- 看到了吗？正常显示的那张图片的HTTP请求中没有Referer，所以我们得到了正常的结果

- 那么问题来了——Referer是怎么去除的呢？
这里我用到了 referrer-killer ，
粗看referrer-killer的原理并不复杂，动态生成了一个iframe，并在这个iframe里面加入img标签来进行显示。

- 等等，如果仅仅是这样的话Referer依然会存在，要么是iframe父页面的地址，要么是iframe属性中src的地址，详情请戳 What is the HTTP Referer if the link is clicked in an <iframe>? ，里面有详细解释。
再仔细看代码，发现iframe中src的值为 javascript:"<!doctype html>......" ，原来是把iframe中的HTML代码全部放到了src中，使用这种方法就可以去掉Referer。其实早有大神给出了方法，只不过没有工具化罢了，详情请戳 json hijack如何丢掉referer 


### 其它伪造http-referer的方法
```
ASP:

dim http
set http=server.createobject("MSXML2.XMLHTTP") '//MSXML2.serverXMLHTTP也可以
Http.open "GET",url,false
Http.setRequestHeader "Referer","http://www.05809.cn/"
Http.send()

PHP(前提是装了curl):

$ch = curl_init();
curl_setopt ($ch, CURLOPT_URL, "http://www.05809.cn/xxx.asp");
curl_setopt ($ch, CURLOPT_REFERER, "http://www.05809.cn/");
curl_exec ($ch);
curl_close ($ch);

PHP(不装curl用sock)

$server = 'www.dc9.cn';
$host = 'www.dc9.cn';
$target = '/xxx.asp';
$referer = 'http://www.dc9.cn/'; // Referer
$port = 80;
$fp = fsockopen($server, $port, $errno, $errstr, 30);
if (!$fp)
{
echo "$errstr ($errno)<br />\n";
}
else
{
$out = "GET $target HTTP/1.1\r\n";
$out .= "Host: $host\r\n";
$out .= "Cookie: ASPSESSIONIDSQTBQSDA=DFCAPKLBBFICDAFMHNKIGKEG\r\n";
$out .= "Referer: $referer\r\n";
$out .= "Connection: Close\r\n\r\n";
fwrite($fp, $out);
while (!feof($fp))
{
echo fgets($fp, 128);
}
fclose($fp);
}

VB.NET/C#.NET

Dim oXMLHttp As MSXML2.XMLHTTP30 = New MSXML2.XMLHTTP30()
或者
MSXML2.XMLHTTP30 oXMLHttp = new MSXML2.XMLHTTP30();
oXMLHttp.open(....
oXMLHttp.setRequestHeader(...
oXMLHttp.send(..
```

原理都是sock构造http头来senddata。其他语言什么的比如perl也可以,

目前比较简单的防御伪造referer的方法是用验证码（Session）。

现在有一些能防盗链软件的商业公司比如UUDOG，linkgate，VirtualWall什么的，都是开发的应用于IIS上面的dll。
有的是采用cookies验证、线程控制，有的是能随机生成文件名然后做URL重写。有的方法能的确达到不错的效果.

不过道高一尺，魔高一丈，这些雕虫小技终归是有破解方法的。


### Javascript document.referer来路判断

- 众所周知，服务器端的referer来路是可以伪造的，无论是ASP、PHP还是其他脚本都是可以伪造referer的，一些下载软件更是把referer伪造的惟妙惟肖，利用webbrowser控件可以方便的伪造来路。
那么，作为保护网站的守门人，我们又应该如何防止这些伪造的referer呢？     
     
- 上面提到的伪造referer的方法都是通过服务器端的脚本来实现的，但它们并不能欺骗客户端。而JS是在客户端执行的，它并不会理会服务器端的headers信息，
所以，利用js的 document.referer 方法可以准确地判断网页的真实来路。     
几乎所有的第三方统计不约而同地采用了 document.referer 来判断来路，为什么？正是基于 js 下的 referer来路 是不可伪造的(不是100%啊，下面就有例子)。即使在服务器端成功地伪造了referer的网页脚本，在第三方统计里也是无法被统计到的，
原因正是由于这些三方统计采用了 document.referer 来判别真实的来路。     所以，为了对抗虚假的 referer 伪造信息，我们只需要利用 js 的 document.referer 来判别，就可以将伪造的信息拒之门外，从而更好的保护站内资源。  

### JavaScript 伪造 Referer 来路方法

- 因为服务器端脚本可以轻易伪造referer，所以各大统计站点cnzz,百度统计，ga,51la等都是通过js来判断来路，不过现在有个方法js的referer也可以伪造了。

- WinHttp.WinHttpRequest.5.1 是 msxml 4.0 的底层对象，也就是说 XMLHTTP/ServerXMLHTTP 也是在它的基础上封装而来。用 WinHttpRequest 发的请求，Fiddler 监测不到。

- Google一下发现它居然用可以成功伪造所有 http 请求的 header 信息！下面的代码通过伪造 referer 的值，假装从百度首页提交一个表单到指定的 url 去：
```
var url = "http://www.yourtarget.com";  
var param = "name=david&age=30";  
var obj = new ActiveXObject("WinHttp.WinHttpRequest.5.1");  
obj.Open("POST", url, false);  
obj.Option(4) = 13056;  
obj.Option(6) = false; //false可以不自动跳转，截取服务端返回的302状态。  
obj.setRequestHeader("Content-Type","application/x-www-form-urlencoded");  
obj.setRequestHeader("Referer", "http://www.baidu.com");  
obj.Send(param);  
WScript.Echo(obj.responseText);
```
- 既然可以用它来伪造所有 http 请求的 header，那 Cookies、Sessionid 自然也就可以得到并传递了。下面是实战代码，用命令行登录博客园，共三次请求，第一次请求获取表单的 VIEWSTATE 和 EVENTVALIDATION，第二次带账户登录，第三次带Cookie访问其首页：

- 上面的代码其实已经有一定恶意，只为证明使用 WinHttpRequest 确实可以模拟浏览器发送请求，服务端也无法区别是从浏览器来的，还是从命令行来的。

- 结论：从客户端提交来的任何数据都不可信，因为发送的 http 数据包不但表单值可以修改，连数据包的 header 都可以随意修改。同时也说明，使用 VIEWSTATE 对表单的安全性无任何用处。
--- end ---