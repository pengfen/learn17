#!/usr/bin/python
#coding=utf-8

# 一个模块是由python代码的文件 一个模块可以定义函数 类和变量
# 导入模块
import module.support

module.support.print_func("ricky") # 调用导入的模块中的函数

from module.support import print_func
print_func("ricky")

# 模块和包 在python中一个文件可以被看成一个独立模块 而包对应着文件夹 模块把python代码分成一些有组织的代码段 通过导入的方式实现代码重用

# 导入有二种方式
# 1. 使用import导入 import module1, module2, module3
# 2. 使用from-import语句导入模块的属性 from module import name1, name2, name3
