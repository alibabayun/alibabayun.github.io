---
layout: post
title: "java学习笔记"
keywords: ["java"]
description: "java学习笔记"
category: "java"
tags: ["java"]
---

### [234集](https://www.bilibili.com/video/BV1Kb411W75N?p=234)

- 只能在同一个类中最多只能调用`n-1`次
- 只能放首行如下
  ```
  public Persion(){}
  public Persion(int age){
  	this();
  }
  ```

### [235集](https://www.bilibili.com/video/BV1Kb411W75N?p=235)
- 将类中常用的 `属性` 快速生成`getName`和`setName`方法
- 将类中常用的 `构造器` 快速生成 空参或多参 的`构造器`
> - mac `alt+command+s`
  - win `alt+shift+s`
- 对象排序（如，外卖app导示中，名称-图标-销售-热度 可当作一个对象，可以根据对象中的不同属性来rd）
	```
//对象排序
public int compose(Girl girl) {
	if(this.age > girl.age) {
		return 1;
	}else if(this.age < girl.age) {
		return -1;
	}else {
		return 0;
	}
}

	```

### [238集](https://www.bilibili.com/video/BV1Kb411W75N?p=238)
- 格式化代码快捷键
> - mac `command+shift+f`
  - win `ctr+shift+f`
- 全类名方式导包
	```
	com.test.exer.Accout acc = new com.test.exer.Accout(double amy);
- `import static`可以导入指定的类或接口中的静态结构
	
	```

### [244集](https://www.bilibili.com/video/BV1Kb411W75N?p=244)
- 常用快捷键盘
	```
/*
 * Eclipse中的快捷键：
 * 1.补全代码的声明：alt + /
 * 2.快速修复: ctrl + 1  
 * 3.批量导包：ctrl + shift + o
 * 4.使用单行注释：ctrl + /
 * 5.使用多行注释： ctrl + shift + /   
 * 6.取消多行注释：ctrl + shift + \
 * 7.复制指定行的代码：ctrl + alt + down 或 ctrl + alt + up
 * 8.删除指定行的代码：ctrl + d
 * 9.上下移动代码：alt + up  或 alt + down
 * 10.切换到下一行代码空位：shift + enter
 * 11.切换到上一行代码空位：ctrl + shift + enter
 * 12.如何查看源码：ctrl + 选中指定的结构   或  ctrl + shift + t
 * 13.退回到前一个编辑的页面：alt + left 
 * 14.进入到下一个编辑的页面(针对于上面那条来说的)：alt + right
 * 15.光标选中指定的类，查看继承树结构：ctrl + t
 * 16.复制代码： ctrl + c
 * 17.撤销： ctrl + z
 * 18.反撤销： ctrl + y
 * 19.剪切：ctrl + x 
 * 20.粘贴：ctrl + v
 * 21.保存： ctrl + s
 * 22.全选：ctrl + a
 * 23.格式化代码： ctrl + shift + f
 * 24.选中数行，整体往后移动：tab
 * 25.选中数行，整体往前移动：shift + tab
 * 26.在当前类中，显示类结构，并支持搜索指定的方法、属性等：ctrl + o
 * 27.批量修改指定的变量名、方法名、类名等：alt + shift + r
 * 28.选中的结构的大小写的切换：变成大写： ctrl + shift + x
 * 29.选中的结构的大小写的切换：变成小写：ctrl + shift + y
 * 30.调出生成getter/setter/构造器等结构： alt + shift + s
 * 31.显示当前选择资源(工程 or 文件)的属性：alt + enter
 * 32.快速查找：参照选中的Word快速定位到下一个 ：ctrl + k
 * 
 * 33.关闭当前窗口：ctrl + w
 * 34.关闭所有的窗口：ctrl + shift + w
 * 35.查看指定的结构使用过的地方：ctrl + alt + g
 * 36.查找与替换：ctrl + f
 * 37.最大化当前的View：ctrl + m
 * 38.直接定位到当前行的首位：home
 * 39.直接定位到当前行的末位：end
 */
	
	```