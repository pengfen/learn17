#!/usr/bin/python
#coding=UTF-8 (脚本中有汉字需要添加此行)

# 1. 注释

# 第一个注释
print "welcome to python world"

# python2中 print是一个关键字 python3中 print是一个函数 必须使用 print(arg)
if True:
    print "True"
else:
    print "false"

# 2. 变量

# 变量定义和赋值
counter = 100 # 整型
miles = 1000.0 # 浮点
# 注释可能会在声明中表达或同一行之后
name = "caopeng" # 注释---解释声明

print counter
print miles
print name

# 多重赋值
a = b = c = 1
d,e,f = 1, 2, "ricky"
print a
print b
print c
print d
print e
print f

# 3. 字符串

# 字符串的使用
str = "welcome to python world" # 字符串在python中本质上是一个字符序列seq
print str # 打印整个字符串
print str[0] # 打印字符串第一个字母
print str[2:5] # 打印第三到第五个字母
print str[2:] # 打印从第三个字母到末尾
print str * 2 # 字符串重复二次
print str + "test" # 字符串拼接

# 4. 列表

# 列表的使用
list = ['abcd', 786, 2.23, "ricky", 70.2]
tinylist = [123, "ricky"]

print list # 打印整个列表
print list[0] # 打印第一个元素
print list[1:3] # 打印第二和第三个元素
print list[2:] # 打印第三个元素到末尾
print tinylist * 2 # 打印二次
print list + tinylist # 拼接两个list

# 修改list中的元素
list[0] = "python"
print list

# 5. 元组

# 元组使用
# 元组是类似于列表中的序列数据类型 一个元组由数个逗号分隔的值组成 列表和元组之间的主要区别是 列表用方括号[] 列表的长度和元素值是可以改变的
# 而元组用圆括号() 不能被更新
tuple = ( 'abcd', 786 , 2.23, 'ricky', 70.2)
tinytuple = (123, 'ricky')

print tuple           # 打印整个元组
print tuple[0]         # 打印第一个元素
print tuple[1:3]       # 打印第2、3两个元素
print tuple[2:]        #
print tinytuple * 2     # 重复2遍
print tuple + tinytuple  # 拼接

# 6. 字典
# 字典是一种哈希表型 由 键-值 对组成  键可以是任何python类型 通常是数字或字符串 值可以是任意python的对象
# 字典是由花括号() 可分配值 并用方括号[]访问
dict = {}
dict["one"] = "this is one"
dict[2] = "this is two"

tinydict = {"name":"ricky", "code":6734, "dept":"sales"}

print dict["one"]
print dict[2]
print tinydict
print tinydict.keys()
print tinydict.values()

# 7.set

a = {1, 2, 3, 4, 5} # 定义一个set
print a
a.remove(3)
print a
a.add(6)
print a
#a.union(b)

# 8. 数据类型转换
# int(x [,base])	     将x转换为整数。基数指定为base（进制）
# long(x [,base] )	     将x转换为一个长整数。基数指定为base，
# float(x)	             将x转换到一个浮点数。
# complex(real [,imag])	 创建一个复数。
# str(x)	             转换对象x为字符串表示形式。
# eval(str)  	         计算一个表达式字符串，并返回一个对象。
# tuple(s)	             把s（序列）转换为一个元组。
# list(s)	             把s（序列）转换为一个列表。
# set(s)	             把s（序列）转换为一个set集合。
# dict(d)	             转成字典,d必须是（键，值）元组序列。

b=int('A', 16)
print(b)

b=tuple(range(1,10,2))
print(b)

b=tuple("hello")
print(b)
c=complex(1,2)
print c

x=1
e=eval('x+1')
print e

f=dict([(1,2),(3,4),('a',100)])
print f