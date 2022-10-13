---
layout: post
title: "python继承"
keywords: ["python"]
description: "python继承"
category: "python"
tags: ["python"]
---

### 前言

要想了解python的继承，请先熟悉下 [Python的MRO](https://blog.csdn.net/come_from_pluto/article/details/90483447)，然后需要使用`Python Mixins 机制`来解决这些问题

#### 普通父子继承

```python
class Flow(): # 兼容python2 都加上这个，注意子类不能加object
    def __init__(self):
        print("Flow父类")


class FlowSub1(object, Flow):
    name = None

    def __init__(self, name):
        super(FlowSub1, self).__init__()
        self.name = name
        print('FlowSub1  init')

    def test(self):
        print('FlowSub1 test', self.name)

    pass


sub1 = FlowSub1('name')

sub1.test()

```



##### 多继承

⼀个类同时继承多个类，称为多继承。 class 类名（⽗类名1，⽗类名2…）
多继承关系中，当多个⽗类具有同名的成员，⼦类调时该成员时先调⽤继承关系中的第⼀个声明的类的成员

类名.__mro__ :查看继承关系

菱形继承问题的发生：
定义：两个子类继承同一个父类，而又有子类同时继承这两个子类

参考资料：

[python之Mixins机制](https://zhuanlan.zhihu.com/p/179265105)

[Python Mixins 机制](https://www.cnblogs.com/liujiacai/p/10241478.html)

```python
class MappingMixin: #这个类可以让子类拥有像 dict 一样调用属性的功能
    def __getitem__(self, key):
        return self.__dict__.get(key)

    def __setitem__(self, key, value):
        return self.__dict__.set(key, value)

class Person(MappingMixin):
    def __init__(self, name, gender, age):
        self.name = name
        self.gender = gender
        self.age = age


p = Person("小陈", "男", 18)
print(p['name'])  # "小陈"
print(p['age'])  # 18

```



### 抽象基类, 类似java中的interface

```python
import abc
class A(abc.ABC):#自己定义一个抽象基类,
    @abc.abstractmethod
    def eat(self):
        pass
    
class B(A):#继承抽象基类
    def voice(self):
        pass
        
if __name__ == "__main__":
    a = A() 
#报错，抽象类无法实现实例化
#TypeError: Can't instantiate abstract class A with abstract methods eat
    a = B()
    a.eat()
#报错，继承类必须实现抽象类的方法
#TypeError: Can't instantiate abstract class B with abstract methods eat
```

