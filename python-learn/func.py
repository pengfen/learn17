#!/usr/bin/python
#coding=utf-8

# 1. 函数块以关键字def后跟函数名为定义头
# 2. 任何输入参数或参数应该放在这些括号内 还可以定义这些括号内的参数
# 3. 函数的第一个语句可以是一个可选的声明 该函数或文档字符串的文档字符串
# 4. 每个函数中的代码块以冒号(:)开头并缩进
# 5. 该语句返回[表达式]退出功能 可选地传递回一个表达式给调用者 不带参数return语句返回none

# 定义函数
def changeme(mylist):
    mylist.append([1, 2, 3, 4])
    return (mylist, "welcome")

# 调用函数
mylist = [10, 20, 30]
changeme(mylist)
print mylist

# 默认参数和可变参数 (有默认值的参数后面不能再跟无默认值的参数)
def printinfo(name, age = 35):
    print "名字:",name;
    print "年龄:",age
    return

printinfo(age=50, name="ricky") # 如果调换了参数的顺序 则必须把参数名都带上
printinfo(name="ricky")

# 可变参数
def printinfo(arg1, *vartuple):
    print arg1
    for var in vartuple:
        print var
    return

printinfo(10)
printinfo(50, 60, 70)

# 匿名函数
# 可以使用lambda关键字来创建小的匿名函数 这些函数被称为匿名 因为它们不是以标准方式通过使用def关键字声明
# lambda形式苛以采取任何数量的参数 但在表现形式上只返回一个值 它们不能包含命令或多个表达式
# 匿名函数不能直接调用打印 因为需要lambda表达式
# lambda函数都有自己的命名空间 并且不能访问变量高于在参灵长列表和那些在全局命名空间的变量

sum = lambda arg1, arg2: arg1 + arg2

print sum(10, 20)
print sum(20, 30)

# 返回多个值
tup = lambda x,y:(x + 1, y + 1)
c = tup(2, 3)
print c[0], c[1]
(a, b) = tup(2, 3)
print a, b
print c[0], c[1]

# 利用lambda可以实现类似于scala中的高阶函数效果
def outfunc(func, x, y):
    c = func(x, y)
    print c

outfunc(lambda x, y: x + y, 1, 2)