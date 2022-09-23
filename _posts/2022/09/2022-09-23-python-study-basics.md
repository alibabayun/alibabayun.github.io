---
layout: post
title: "python学习基础篇-常用语法"
keywords: ["python"]
description: "python学习基础篇-常用语法"
category: "python"
tags: ["python"]
---

## 格式化字符
```
info = '{0:*^10}'.format('开始')
info2 = '{num:.2f}'.format(num=3.1415925)
```

## 常用数据类型
```
列表 list  []      [1,2,3]
字典 dict  {}      {'name':1, 'age':17}
集合 set   set()   {1,2,3}
元组 tuple ()      ('a', 19)
```

## 集合运算
```
交集 &
并集 |
差集 -
对称差集 ^
set1 = {1, 2, 3}
set2 = {1, 2}
print(set1 - set2)
```

## 父子集 > < =
```
set1 = {1, 2, 3}
set2 = {1, 2}
print(set1 > set2)
```

## 删除字典中key，没有也不会报错
```
s1 = {1,3,4}
s1.discard(1)
print(s1)
ist1 = ['Google', 'Runoob', 'Taobao']
list_pop=list1.pop(1)
print ("删除的项为 :", list_pop) #删除的项为 : Runoob
print ("列表现在为 : ", list1)   #列表现在为 :  ['Google', 'Taobao']
```

## 判断指定集合的所有元素是否都包含在原始的集合中，如果是则返回 True，否则返回 False
```
s2 = {1,3,4,44}
print(s2.issuperset(s1))
```

## 从文件中读取用户密码
```
input_username = input("请输入账号：")
input_passwd   = input("请输入密码：")
with open('data/user.txt', mode='rt', encoding='utf-8') as f:
    for user in f:
        username, passwd = user.strip("\n").split('----')
        if username == input_username and passwd == input_passwd:
            print("登陆成功")
            break
    else:
        print("账号密码不正确")
```

## 修改文本中的文字
```
import os
with open('data/user.txt', mode='rt', encoding='utf-8') as f,\
    open('data/.user.txt.tmp', mode='wt', encoding='utf-8') as f1:
    for line in f:
        res = line.replace('admin123','admin')
        f1.write(res)
os.remove('data/user.txt')
os.rename('data/.user.txt.tmp','data/user.txt')
```

## 拆分txt文件中的账号密钥
```
with open("data/user.txt", mode='rt', encoding='utf-8') as f:
    for line in f:
        username, passwd = line.strip("\n").split("----")
        print(f'用户名{username} 密码{passwd}')
```

## 分页读
```
with open("data/user.txt", mode='rb') as f1:
    while True:
        read = f1.read(1024).decode('utf-8')
        if not read:
            break
        print(f'data: {read}')
```

## 判断 字典或元组中是否有这个值
```
t = {22,22,'a2'}
iskey = 'a2' in t
print(iskey)

t = (22,22,'a2')
iskey = 'a2' in t
print(iskey)
```

## 装饰器
```
import  time
def show(num):
    for i in range(num, 101):
        time.sleep(0.1)
        print(f'\r当前电量 {"*"*i}{i}%', end='')
    print("")
#
def wrapper(func):
    def time_show(*args, **kwargs):
        start = time.time()
        func(*args, **kwargs)
        end  = time.time()
        print("time:", end - start)
    return time_show
#
show = wrapper(show)
show(20)
```

## 无参装饰器 语法糖
```
import  time
#
def count_time_wrapper(func):
    def time_show(*args, **kwargs):
        start = time.time()
        func(*args, **kwargs)
        end  = time.time()
        print("time:", end - start)
    return time_show
#
@count_time_wrapper
def show(num):
    for i in range(num, 101):
        time.sleep(0.1)
        print(f'\r当前电量 {"*"*i}{i}%', end='')
    print("")

show(20) #对show方法扩展
```

## 有参装饰器
```
def auth(username, passwd):
    def wrapper(func):
        def check(*arg, **kwargs):
            func(*arg, **kwargs)
            print(f"登陆验证{username}")
        return check
    return wrapper
#
@auth('admin', '123456')
def home(num):
    print(f"welcome{num}")
#
@auth('admin2', '123456')
def home2(num):
    print(f"passwd{num}")
home(123)
home2(456)
```

## 装饰器迭加
```
def outer1(func):
    def wrapper(*args, **kwargs):
        print("outer1开始")
        respose = func(*args, **kwargs)
        print("outer1结束")
        return respose
    return wrapper
def outer2(year):
    def outer(func):
        def wrapper(*args, **kwargs):
            print("outer2开始", year)
            respose = func(*args, **kwargs)
            print("outer2结束", year)
            return respose
        return wrapper
    return outer
def outer3(func):
    def wrapper(*args, **kwargs):
        print("outer3开始")
        respose = func(*args, **kwargs)
        print("outer3结束")
        return respose
    return wrapper
@outer3
@outer2(2022)
@outer1
def home(num):
    print(f'当前编号:{num}')
home(0)
```

## 迭代器
```
l = [2, 3, 4]
iter = l.__iter__()
for i in iter:
    print(i)
iter = l.__iter__()
while True:
    try:
        print(iter.__next__())
    except StopIteration:
        break
```

## yield 关键字
```
def hello():
    print("echo 1")
    yield 1
    print('echo 2')
    yield 2
    print('echo 3')
    yield 3
g = hello()
iter = g.__iter__()
while True:
    try:
        print(iter.__next__())
    except StopIteration:
        break
```

## yield 其它用法
```
def func(x):
    print(f'{x}图片下载器')
    while True:
        y = yield None
        time.sleep(1)
        print('\n文章id：',x,'下载图片地址：',y,'\n')
g = func(1)
next(g)
g.send('图片地址1')
g.send('图片地址2')
print("当前图片已进入协程下载中")
````

## 三元表达式
```
if 1 == 1: #原来写法
    res = 'yes'
else:
    res = 'no'
print(res)
#三元写法：为真结果 if 条件  else  为false返回值
res = 'yes' if 1 == 1 else 'no'
print(res)
```
## 列表生成式
```
l = ["牛肉面", '鸡蛋面', '热干面', '炒米粉', '包子']
l_new = [] #找出最后一个是面的食物
for x in l: #原始写法
    if x.endswith('面'):
        l_new.append(x)
print(l_new)
l_new = [ x for x in l if x.endswith("面")] #列表生成式写法
print(l_new)
l1 = [ x for x in l if x.endswith("粉")]
print(l1)
```

## 字典生成式
```
l = [("冰", 2), ("华子", 100), ("玉米", 4)]
dict = {}
for x in l:  #原来写法
    if x[0].startswith('玉米'):
        dict.update({x[0]: x[1]})
print(dict)
#
dict_new = {k: v for k, v in l if not k.startswith('玉米')} #字典生成式 写法
print(dict_new)
```

## 元组生成式（生成式表达式）
```
t = (2, 3, 4)
t_new = (x for x in t)
print(t_new) #<generator object <genexpr> at 0x10be86b30> 可迭代对象 不消耗内存
print(next(t_new))
print(t_new.send(None))
#
with open("data/user.txt", mode='rt', encoding='utf-8') as f:
    #统计文本文件中的字符数
    size = sum( (len(line) for line in f) ) #这里将line里面转成一个可迭代对象，内存消耗少
    print(size)
```

## 匿名函数,(临时用一次 用完后内存地址销掉)
```
info = {
    'Tom': 23,
    'Jim': 33,
    'Hol': 20
}
m = max(info, key=lambda k: info[k]) #取最大值的名字
print(m)
l = [('张三', 11), ('李四', 9), ('王五', 33)]  #按每个元组中的数值排序
l.sort(key=lambda item: item[1], reverse=True)
print(l)
```

## map函数的使用
```
l = ["康师傅", "老谭酸菜", '白q']
l_new  = [name+'面' for name in l] #在每个名称里最后加一个面条
print(l_new)
#
m = map(lambda name: name + '面', l)
print(m) #<map object at 0x10c6b1f10> 这里是一个可迭代对象了
for k in m:
    print(k)
```

## 解释弄强类型语言，类型提示功能
```
def func(name: str, age: int):
    print(name, age)

func('name', 3)
```