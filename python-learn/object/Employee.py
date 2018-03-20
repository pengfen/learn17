#!/usr/bin/python
#coding=utf-8

# 使用class来创建一个新类 class之后为类的名称并以冒号结尾

class Employee:
    empCount = 0

    # 构造函数
    def __init__(self, name, salary):
        self.name = name
        self.salary = salary
        Employee.empCount += 1

    def displayCount(self):
        print("员工总数 %d" % Employee.empCount)

    def displayEmployee(self):
        print("名字:", self.name, ", Salary:", self.salary)

# "创建 Employee 类的第一个对象"
# emp1 = Employee("Zara", 2000)
# "创建 Employee 类的第二个对象"
# emp2 = Employee("Manni", 5000)
# emp1.displayEmployee()
# emp2.displayEmployee()