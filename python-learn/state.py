#!/usr/bin/python
#coding=utf-8

# 1.if 语句
var1 = 100
if var1:
    print var1

# 条件如果是数字 则只要不等于0 就为true  如果是字符串 则只要不是空串 就为true
var2 = 0
if var2:
    print var2
print "结束"

# 2.if else 语句
var = 100
if var == 200:
    print var
elif var == 150:
    print var
elif var == 100:
    print var
else:
    print var

var = 100
if var < 200:
    print "小于200"
    if var == 150:
        print "等于150"
    elif var == 100:
        print "等于100"
    elif var == 50:
        print "等于50"
elif var < 50:
    print "小于50"
else:
    print "错误"

# 3. while 循环
count = 0
while count < 5:
    print "小于5"
    count = count + 1
else:
    print "大于等于5"

# 4. for 循环
for num in range(10, 20):
    for i in range(2, num):
        if num % i == 0:
            j = num / i
            print "%d == %d * %d" % (num, i, j)
            break
    else:
        print "素数:", num

r = {1, 2, 3, 4, 5}
for num in r:
    print num

r = ["aaa", 3, "c"]
for num in r:
    print num

r = {"a":9, "b":10}
for num in r:
    print num
for num in r.values():
    print num