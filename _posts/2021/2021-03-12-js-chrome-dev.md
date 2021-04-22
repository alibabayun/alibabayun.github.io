---
layout: post
title: "检测谷歌浏览器和火狐浏览器是否开了开发者工具"
keywords: ["js"]
description: "检测谷歌浏览器和火狐浏览器是否开了开发者工具"
category: "js"
tags: ["js"]
---
禁用F12按键、无限 debugger 这种比较容易突破的方法我就不贴了，这里贴两个难突破点的，大家可以尝试挑战一下。


```
/**
 * 检测到调试时进行的操作
 */
let onDebug = function () {
    document.write('检测到非法调试！请停止调试后刷新本页面！');
};

/**
 * 当dom被发送至控制台时(例如console.log)
 * 浏览器会自动通过该dom的getter()获取该 dom 的id
 * 所以我们可以创建一个dom，然后发送到控制台，并且重定义其getter()
 *
 * 缺点：这招对火狐没用
 */
let div = document.createElement('div');
Object.defineProperty(div, "id", {
    get: () => {
        clearInterval(loop);
        onDebug();
    }
});
let loop = setInterval(() => {
    console.log(div);
    console.clear();
});

/**
 * 上面那个方法支持谷歌而不支持火狐
 * 这次来个支持火狐不支持谷歌的
 */
let firefox_test=/./;
firefox_test.toString = function(){
    onDebug();
}
/**
 * 对于火狐：若开发者工具开启，则下面这行代码会触发 firefox_test.toString，否则反之
 * 对于谷歌：无论是否开启开发者工具，都会触发
 */
console.log(firefox_test);
 ```
参考 ：https://hu60.cn/q.php/bbs.topic.99256.html
