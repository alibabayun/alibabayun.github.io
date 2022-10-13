---
layout: post
title: "python基础二"
keywords: ["python"]
description: "python基础二"
category: "python"
tags: ["python"]
---



### property 装饰器

```python
class Demo(object):
    __age = None #加__变成内部隐藏属性

    def __init__(self):
        pass

    @property
    def age(self):
        return self.__age

    @age.setter
    def age(self, age):
        if age < 0 or age > 150:
            print("age参数格式不正确")
        else:
            self.__age = age


demo = Demo()
demo.age = -1
print(demo.age)
print(demo.__dict__)

```



### 随机数

```python
import random

print(random.random())  # 0-1之间浮点

print(random.uniform(1, 2))  # 取1-2之间的浮点数

print(random.randint(1, 10))  # 10是可以取到的 左闭右闭

print(random.randrange(1, 10))  # 左闭右开 不包含10

print(random.choice([2, 4, 22]))  # 从一个序列中随机选择

print(random.sample(["a", "b", "c"], 2))  # 从列表中随机选多个值

li = [1, 3, 5, 7]
random.shuffle(li)
print(li)  # 打乱 洗牌

### 生成一个16位的包含英文字母大小写数字
pwd = ''
char_list = [[97, 122], [65, 90], [48, 57], [33, 47]]
for _ in range(16):
    rand_list = random.choice(char_list)  # 从列表中随机取一个
    random_str = chr(random.randint(*rand_list))  # 将里面有列表打散 相当 *rand_list = rand_list[0],rand_list[1]
    pwd += random_str
print(pwd)

str = "sfasfasd"
print(str[:4])
```



### 抓取页面中所有图片

```python
import bs4
import requests
import os
req = requests.get(r"http://www.szwed.com.cn/power/p2/preview.html?id=61416&pc=ok")
req.encoding="utf-8"
bs = bs4.BeautifulSoup(req.text)
# print(bs)
obj = bs.find_all("div",{"class":{"wedimg"}})
# print(obj)
objHtml=[]
objImg=[]
for s in obj:
    objHtml.append(s.find("img"))
for o in objHtml:
    objImg.append(o.get("src"))
# print(objImg)
for img in objImg:
    with open("img/"+os.path.basename(img),'wb') as f:
        f.write(requests.get('http:'+img).content)
    print(os.path.basename(img)+"保存成功");
```



### 反射

```python
class ReflexTest:
    age = 1
    pass


obj = ReflexTest()
print(hasattr(obj, 'age'))  # 判断对象中是否有属性
print(getattr(obj, 'age'))  # 获取对句中的属性值

setattr(obj, 'age', 22)  # 设置对象中的属性
print(getattr(obj, 'age'))

delattr(obj, 'age')  # 删除对句中的属性值
print(obj.__dict__)

print(getattr(obj, 'name', None))  # 取对象中的属性，没有返回None

```



### 反射实际应用

```python
class Ftp:

    def put(self):
        print("正在上传数据...")

    def get(self):
        print("正在下载数据...")

    def errpage(self):
        print("方法不存在")

    def interact(self):
        fun = input('请输入功能 >>>')
        # if hasattr(self, fun):
        #     getattr(self, fun)()
        # else:
        #     print("方法不存在")
        getattr(self, fun, self.errpage)()


ftp = Ftp()
ftp.interact()

```



### 类方法引入 

```python
import setup


class Mysql:
    def __init__(self, ip, port):
        self.ip = ip
        self.port = port
        
		def f1(self):
        return ('this is f1')
      
    @staticmethod #静态方法
    def stafun():
        return 'this is static method'

    @classmethod  #类方法引入
    def mysql_instace_conf(cls):
        ip = setup.IP
        port = setup.PORT
        # return Mysql(ip, port)
        return cls(ip, port)

# mysql = Mysql(setup.IP, setup.PORT) #原来需要每个地方
mysql = Mysql.mysql_instace_conf() #类方法引入 
print(mysql.__dict__) # 返回  {'ip': '127.0.0.1', 'port': 3306}

print(mysql.stafun())
```

